
public class Body {

    @SerializedName("items")
    @Expose
    private Items items;
    @SerializedName("numOfRows")
    @Expose
    private Integer numOfRows;
    @SerializedName("pageNo")
    @Expose
    private Integer pageNo;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;



}

public class Example {

    @SerializedName("response")
    @Expose
    private Response response;


}

public class Header {

    @SerializedName("resultCode")
    @Expose
    private String resultCode;
    @SerializedName("resultMsg")
    @Expose
    private String resultMsg;



}

public class Item {

    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("careAddr")
    @Expose
    private String careAddr;
    @SerializedName("careNm")
    @Expose
    private String careNm;
    @SerializedName("careTel")
    @Expose
    private String careTel;
    @SerializedName("colorCd")
    @Expose
    private String colorCd;
    @SerializedName("desertionNo")
    @Expose
    private Long desertionNo;
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("happenDt")
    @Expose
    private Integer happenDt;
    @SerializedName("happenPlace")
    @Expose
    private String happenPlace;
    @SerializedName("kindCd")
    @Expose
    private String kindCd;
    @SerializedName("neuterYn")
    @Expose
    private String neuterYn;
    @SerializedName("noticeEdt")
    @Expose
    private Integer noticeEdt;
    @SerializedName("noticeNo")
    @Expose
    private String noticeNo;
    @SerializedName("noticeSdt")
    @Expose
    private Integer noticeSdt;
    @SerializedName("officetel")
    @Expose
    private String officetel;
    @SerializedName("orgNm")
    @Expose
    private String orgNm;
    @SerializedName("popfile")
    @Expose
    private String popfile;
    @SerializedName("processState")
    @Expose
    private String processState;
    @SerializedName("sexCd")
    @Expose
    private String sexCd;
    @SerializedName("specialMark")
    @Expose
    private String specialMark;
    @SerializedName("weight")
    @Expose
    private String weight;

}

public class Items {

    @SerializedName("item")
    @Expose
    private List<Item> item = null;



}


@Generated("jsonschema2pojo")
public class Response {

    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("body")
    @Exposez
    private Body body;

}