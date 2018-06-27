import os

def separate(word, wordArr):
    cont = 0
    sepWord = []
    for _ in word:
        sepWord.append(_)
        if (_ == " "):
            cont += 1
        if (cont == 2):
            wordArr.append(''.join(sepWord))
            sepWord = []
            cont = 0

a = os.popen('./out.sh').read()
b = []

separate(a, b)
print(b[0])
