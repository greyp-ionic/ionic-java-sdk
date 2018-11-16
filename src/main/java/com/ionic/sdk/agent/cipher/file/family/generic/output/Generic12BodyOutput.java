package com.ionic.sdk.agent.cipher.file.family.generic.output;

import com.ionic.sdk.agent.cipher.file.data.FileCipher;
import com.ionic.sdk.agent.key.AgentKey;
import com.ionic.sdk.cipher.aes.AesCtrCipher;
import com.ionic.sdk.core.annotation.InternalUseOnly;
import com.ionic.sdk.crypto.CryptoUtils;
import com.ionic.sdk.error.IonicException;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Extensions for handling output of {@link com.ionic.sdk.agent.cipher.file.GenericFileCipher}
 * version 1.2 file body content.
 */
@InternalUseOnly
final class Generic12BodyOutput implements GenericBodyOutput {

    /**
     * The raw output data stream that is to contain the protected file content.
     */
    private final BufferedOutputStream targetStream;

    /**
     * The Ionic cipher used to encrypt file blocks.
     */
    private final AesCtrCipher cipher;

    /**
     * The cryptography key used to encrypt and sign the file content.
     */
    private AgentKey key;

    /**
     * A running buffer used to store block hashes.  These are hashed and signed at the completion of the
     * file crypto operation, and the result is prepended to the file content.
     */
    private final ByteArrayOutputStream plainTextBlockHashes;

    /**
     * Constructor.
     *
     * @param targetStream the raw output data containing the protected file content
     * @param cipher       the Ionic cipher used to encrypt file blocks
     * @param key          the cryptography key used to decrypt and verify the file content
     */
    Generic12BodyOutput(final BufferedOutputStream targetStream, final AesCtrCipher cipher, final AgentKey key) {
        this.targetStream = targetStream;
        this.plainTextBlockHashes = new ByteArrayOutputStream();
        this.cipher = cipher;
        this.key = key;
    }

    /**
     * The v1.2 streaming cipher needs to set aside room for the file signature, so that it may be inserted after all
     * of the file data is written.
     *
     * @throws IOException on failure reading from the stream
     */
    @Override
    public void init() throws IOException {
        targetStream.write(new byte[FileCipher.Generic.V12.SIGNATURE_SIZE_CIPHER]);
    }

    @Override
    public int getBlockLengthPlain() {
        return FileCipher.Generic.V12.BLOCK_SIZE_PLAIN;
    }

    @Override
    public void write(final byte[] block) throws IOException, IonicException {
        final byte[] plainTextBlockHash = CryptoUtils.hmacSHA256(block, key.getKey());
        plainTextBlockHashes.write(plainTextBlockHash);
        final byte[] cipherTextBlock = cipher.encrypt(block);
        targetStream.write(cipherTextBlock);
        targetStream.flush();
    }

    @Override
    public void doFinal() {
    }

    /**
     * The v1.2 streaming cipher contains a file signature, used to validate decryption of the content.
     *
     * @throws IonicException on signature generation failure
     */
    @Override
    public byte[] getSignature() throws IonicException {
        final byte[] blockHashes = plainTextBlockHashes.toByteArray();
        final byte[] hmacSHA256 = CryptoUtils.hmacSHA256(blockHashes, key.getKey());
        return cipher.encrypt(hmacSHA256);
    }
}