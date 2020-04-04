# GithubTest

 这是一个用于垂直滑动的ScrollView
 
 包含了滑动方向判断，是否滑动到底部判断，是否处于滑动判断
 
 是否滑动到顶部可以用getScrollY()==0作为判断依据，所以不单独提供判断
 
 也包含一个滑动冲突实例
 
依赖方式1：

Step1（第一步）：

在项目根gradle下添加：

	allprojects {
  
		repositories {
    
			...
      
			maven { url 'https://jitpack.io' 
      }
      
		}
	}
  
 Step2(第二步)
 
 在app的gradle下添加：
 
 dependencies {
 
	        implementation 'com.github.zhangye1994:GithubTest:1.0.0'
          
	}
  
 依赖方式2：
  
 Step 1. Add the JitPack repository to your build file
  
        <repositories>
	
		<repository>
		
		    <id>jitpack.io</id>
		    
		    <url>https://jitpack.io</url>
		    
		</repository>
		
	</repositories>
  
Step 2. Add the dependency
  
  	<dependency>
	
	    <groupId>com.github.zhangye1994</groupId>
	    
	    <artifactId>GithubTest</artifactId>
	    
	    <version>1.0.0</version>
	    
	</dependency>
  
  
  
  
