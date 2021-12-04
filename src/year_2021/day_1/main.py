inputFile = open('input', 'r')
inputData = inputFile.readlines()
inputFile.close()

increaseCount = 0
lastValue = None

for scanData in inputData:
    scanData = int(scanData)
    print(scanData)
    if (lastValue == None):
        lastValue = scanData
    if (scanData > lastValue):
        increaseCount+= 1;
    lastValue = scanData

print(increaseCount)
