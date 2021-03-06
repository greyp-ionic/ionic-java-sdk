package com.ionic.sdk.cipher.aes.model;

import com.ionic.sdk.agent.AgentSdk;
import com.ionic.sdk.cipher.aes.AesCipher;
import com.ionic.sdk.error.IonicException;

import javax.crypto.KeyGenerator;

/**
 * Class encapsulating capability to generate symmetric cryptography keys.
 */
public class AesKeyGenerator {

    /**
     * Constructor.
     */
    public AesKeyGenerator() {
    }

    /**
     * Creates a new cryptographic key.
     *
     * @return a {@link AesKeyHolder} object containing the newly created key
     * @throws IonicException on cryptography errors
     */
    public final AesKeyHolder generate() throws IonicException {
        final KeyGenerator keygenSymmetric = AgentSdk.getCrypto().getKeyGeneratorAes();
        keygenSymmetric.init(AesCipher.KEY_BITS);
        return new AesKeyHolder(keygenSymmetric.generateKey());
    }
}
