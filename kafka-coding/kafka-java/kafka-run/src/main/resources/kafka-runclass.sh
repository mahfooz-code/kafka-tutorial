#Offset export

#GENERAL
kafka-run-class.sh kafka.tools.ExportZkOffsets \
  --zkconnect zoo1.example.com:2181/kafka-cluster --group testgroup \
  --output-file offsets

#CDH
  kafka-run-class kafka.tools.ExportZkOffsets \
  --zkconnect zoo1.example.com:2181/kafka-cluster --group test \
  --output-file offsets

#Import offset

  #GENERAL
  kafka-run-class.sh kafka.tools.ImportZkOffsets --zkconnect \
  zoo1.example.com:2181/kafka-cluster --input-file offsets

  #CDH
  kafka-run-class kafka.tools.ImportZkOffsets --zkconnect \
  zoo1.example.com:2181/kafka-cluster --input-file offsets



kafka-run-class.sh kafka.tools.DumpLogSegments --files 00000000000052368601.log

kafka-run-class.sh kafka.tools.DumpLogSegments --files 00000000000052368601.log --print-data-log


