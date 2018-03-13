Rem Environment : Local | Changeset : Latest | Build on : 21-June-2016 17:53:18 IST
Rem set classpath=%classpath%;
java -Xms256m -Xmx512m -XX:MaxPermSize=756m -cp .;%OPSERV_HOME%/config;opserv-sp-notification-batch-3.0.jar -Dlog4j.configurationFile=file:///%OPSERV_HOME%/config/nc-batch-log-config.xml -DappType=standalone -DappName=Notification -DtargetDataSource=app -Dopserv.config.file=%OPSERV_HOME%/config/opserv-config.properties  com.cognizant.opserv.sp.notification.util.NotificationBatch


