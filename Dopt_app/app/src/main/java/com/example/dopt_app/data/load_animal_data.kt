package com.example.dopt_app.data
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class load_animal_data {
    fun main() {


        //api 를 신청하게되면 접근할 수 있도록 키값을 준다. 그거 ㄱㄱ
        val key : String = "SSp25i3dc5GkEAwqr6qrKHLAPS7aMZ/aKuVyMlE+qQ1irBnGaQNkbmm24XJF05S42SXMwQIIcIeC+vm6IggUOQ=="
        //api 정보를 가지고 있는 주소
        var url : String="http://openapi.animal.go.kr:80/openapi/service/rest/abandonmentPublicSrvc?_wadl"+key
        //기본적으로 xml형태를 가지게 된다. xml을 파싱하기 위한 코드.
        //만약 본인은 json으로 해야한다! 싶으면 url 끝에 &_type=json 사용. 물론 내 코드에는 안된다.
        val xml : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)


        xml.documentElement.normalize()
        println("Root element : "+xml.documentElement.nodeName)

        //찾고자 하는 데이터가 어느 노드 아래에 있는지 확인
        val list:NodeList=xml.getElementsByTagName("item")


        for(i in 0..list.length-1){
            var n:Node=list.item(i)
            if(n.getNodeType()==Node.ELEMENT_NODE){
                val elem=n as Element
                val map=mutableMapOf<String,String>()


                for(j in 0..elem.attributes.length - 1)
                {
                    map.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)
                }
                //list.length-1 (건축물 대장의 경우 디폴트 = 10)만큼 얻고자 하는 태그의 정보를 가져온다
                println("=========${i+1}=========")
                println("1. 나이 : ${elem.getElementsByTagName("age").item(0).textContent}")
                println("2. 보호소 주소 : ${elem.getElementsByTagName("careAddr").item(0).textContent}")
                println("3. 보호소 이름 : ${elem.getElementsByTagName("careNm").item(0).textContent}"+"${elem.getElementsByTagName("ji").item(0).textContent}")
                println("4. 보호소 전화번호 : ${elem.getElementsByTagName("careTel").item(0).textContent}")
                println("5. chargeNm : ${elem.getElementsByTagName("chargeNm").item(0).textContent}")
                println("6. 색깔 : ${elem.getElementsByTagName("colorCd").item(0).textContent}"+"${elem.getElementsByTagName("atchJi").item(0).textContent}")


            }
        }
    }

}