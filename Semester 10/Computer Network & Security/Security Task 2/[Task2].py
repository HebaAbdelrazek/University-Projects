from Crypto.Cipher import AES
from Crypto import Random
from Crypto.Hash import SHA256
import binascii


def encrypt(plain, key):

    rndfile = Random.new()
    iv = rndfile.read(AES.block_size)

    hashed = hash(plain, iv)
    toBeEncrypted = plain + hashed

    obj = AES.new(key, AES.MODE_CFB, iv)
    ciphertext = obj.encrypt(toBeEncrypted)
    ciphertextIV = iv + ciphertext

    return ciphertextIV


def decrypt(cipher, key):
    iv = cipher[:16]
    ciphertext = cipher[16:]

    obj2 = AES.new(key, AES.MODE_CFB, iv)
    plainwithhash = obj2.decrypt(ciphertext)
    plain = plainwithhash[:-64].decode()
    hash1 = plainwithhash[-64:].decode()

    hash2 = hash(plain,iv)
    if(hash1 == hash2):
        isCorrupt = False
    else:
        isCorrupt = True

    return plain, isCorrupt

def hash(msg, iv):

    toBeHashed = msg + str(iv)
    hash = SHA256.new()
    hash.update(toBeHashed.encode())

    return hash.digest().hex()


if __name__ == "__main__":
    key = 'Sixteen byte key'
    plain = 'Hello from the other side'
    cipher = encrypt(plain, key)
    print('CFB cipher output: ', cipher.hex())
    plain, isCorrupt = decrypt(cipher, key)
    print('CFB plain output: ', plain)
    if(isCorrupt == True):
        print('Message is corrupted')
    else:
        print('Message is received correctly')

    TestCipher = 'ea066beff0e89e9e3eed2c7dde7bc9501b919423f5d59e35cbe85de03e413c502334c504cc235c981c2ad5f251eee5e6e010884ee8c86fe894eaac8403491787b9ce4ce88bafbc0ab2e90c0c987697b2a5bfb0f64eee930d459a8471ade1486bdb7c37f661f04caf97c47b3deba39ab3f1a866ba3b827baa0668ffc6b4d5ff0879d7223600544284a5'
    TestCipher = binascii.a2b_hex(TestCipher)
    CFBplainTest, isCorrupt = decrypt(TestCipher, key)
    print('CFB plain output: ', CFBplainTest)
