cd ../
xcopy "spring-boot-test" "d:\\deploy\\spring-boot-test" /e /y
cd /d d:\\deploy\\spring-boot-test
gradle clean build -x test