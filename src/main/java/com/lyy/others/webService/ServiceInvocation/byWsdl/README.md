> https://blog.csdn.net/VitaminZH/article/details/81123571
# 1、根据wsdl生成客户端代码

    wsimport -s g:\wsdl -p com.example.demo.request -encoding utf-8 http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl
- s 存储目录； 
- p 包名； 
- encoding 文件编码，默认会采用操作系统编码，中文为gbk，建议使用utf-8；
# 2、创建配置类
    @Configuration
		public class IpConfig {
	
	    @Bean
	    public IpAddressSearchWebServiceSoap webService(){
	        return new IpAddressSearchWebService().getIpAddressSearchWebServiceSoap();
	    }
	}
# 3、创建测试类
    @RestController
	@RequestMapping("/soap")
	public class DemoApplication {
	
	    @Autowired
	    private IpAddressSearchWebServiceSoap soap;
	
	    @RequestMapping("/{ip}")
	    public ArrayOfString searchIp(@PathVariable("ip") String ip) {
	        ArrayOfString response = soap.getCountryCityByIp(ip);
	        return response;
	    }
	}
