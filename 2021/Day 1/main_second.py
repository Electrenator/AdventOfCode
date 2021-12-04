inputFile = open('input', 'r')
inputData = inputFile.readlines()
inputFile.close()

increaseCount = 0
valueArray = []
prevValueArray = []

def sumArray(array):
    sum = 0;
    for value in array:
        sum += value
    return sum

for scanData in inputData:
    scanData = int(scanData)

    if (len(valueArray) < 3):
        valueArray.append(scanData)
        continue

    prevValueArray = list(valueArray)
    valueArray.pop(0)
    valueArray.append(scanData)
    
    average = sumArray(valueArray)
    if (average > sumArray(prevValueArray)):
        increaseCount+= 1;
    

print(increaseCount)
