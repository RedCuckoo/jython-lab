import re

filename = input("Enter file name with text: ")
N = int(input("Enter required amount of most frequent words: "))

file = open(filename, "r")
text = file.read()
text = text.lower()
text = re.sub("[^\w\s]", "", text)
word_arr = text.split()

dictionary = dict.fromkeys(word_arr, 0)
# add amount for each word
for v in  word_arr:
    dictionary[v] = dictionary[v] + 1

# sort by value in reversed order
sorted_dictionary = sorted(dictionary.items(), key=lambda kv: kv[1], reverse=True)

result_dictionary = dict()
# create a new dictionary to return
for i in range(N):
    result_dictionary[sorted_dictionary[i][0]] = sorted_dictionary[i][1]

print(result_dictionary)