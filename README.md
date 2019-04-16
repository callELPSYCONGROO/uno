# uno
>你懂的

本项目为爬虫（crawler） + 信息持久化（save） + 下载（download） + 展示（showtime）。

craler采用[gecco-spring](https://github.com/xtuhcy/gecco-spring)框架进行页面内容爬取，获取到的内容通过[rabbitmq](https://github.com/rabbitmq/rabbitmq-server)采用topic模式向download、save分发数据，showtime作为数据展示。所有项目均使用[spring-boot](https://github.com/spring-projects/spring-boot)框架构建。

