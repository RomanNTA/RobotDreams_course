package cz.robodreams.javadeveloper.homeworks.hw04ifbit;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//@Disabled
class CypherTextTest {

    CypherText cypherText = new CypherText();


    @Test
    void encryptAndDecryptByXor() {

        int encrypt = cypherText.encryptByXor(20, 1);
        String binaryString = Integer.toBinaryString(encrypt);
        assertThat(binaryString)
                .as("Comparing binary representation of encrypted char to expected")
                .isEqualTo("10101");

        assertThat(cypherText.decryptByXor(encrypt, 1))
                .isEqualTo(20);
    }
}
