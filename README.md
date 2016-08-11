# COSBatchDownload
@author 李大帅
  
  由于官方并没提供合适的批量下载工具，于是自己写了一个腾讯云COS对象储存批量下载程序，能实现一键下载目录下的所有文件（包括子孙目录中的文件），方便转移数据。
  
###   使用方法
参见demo，
  
  第一步：首先配置config.xml文件。
  
-   AppID、secretID、secretKey请参照各自的COS账户填写
-   bucketName是要下载的根目录所属COS bucket名称
-   remotePath是要下载的远程根目录
-   localPath是下载文件夹本地保存路径
```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ns2:config xmlns:ns2="com.qcloud.cosapi">
    <AppID>10003421</AppID>
    <secretID>AKIDLGvDdsE7YnjeSg7GRdaiuHy7rF0laYBR</secretID>
    <secretKey>ik1ozSOu4hUDHzT7UBJntQtoUCveK2UN</secretKey>
    <bucketName>duanzishou</bucketName>
    <remotePath>/</remotePath>
    <localPath>F://COS</localPath>
</ns2:config>
```
  
  第二步：双击start.bat文件即开始文件下载，控制台中会实时显示下载进度。
