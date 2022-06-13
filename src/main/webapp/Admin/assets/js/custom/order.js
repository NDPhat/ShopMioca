var order1 = {
    Id : -1,
    CustomerName : "None",
    Total : 1000,
    Date : "01.01.2001",
    Status : "Publish",
}
const order_table = document.getElementById("order-table");
const show = document.getElementById("order-table");
const listshow1 = document.getElementsByClassName("show-item");
const pagination_space_1 = document.getElementById("pagination_space_1");
const pagination_space_2 = document.getElementById("pagination_space_2");
const pagination_first = document.getElementById("pagination_first");
const pagination_2 = document.getElementById("pagination_2");
const pagination_3 = document.getElementById("pagination_3");
const pagination_4 = document.getElementById("pagination_4");
const pagination_last = document.getElementById("pagination_last");
const pagination_previous = document.getElementById("previous");
const pagination_next = document.getElementById("next");
const show_infos= document.getElementById("info-show");
var list_pagination =[pagination_first,pagination_2,pagination_3,pagination_4,pagination_last];
var max_page_num =1 ;
var num = 10;
var curent_page = 1 ;
var MyApp = MyApp || {};
function fnInit(csrfParam, csrfToken) {
    MyApp.csrfToken = {
        param : csrfParam,
        value : csrfToken
    }
}
function Render(order){
    let tempcolor ="orange";
    if(order.Status == "Complete")
        tempcolor="green";
    if(order.Status == "Canceled")
        tempcolor="red";
    let content =  "  <tr class=\"table__row\">\n" +
        "                                    <td class=\"d-none d-lg-table-cell table__td\"><span class=\"text-grey\">#"+order.Id+"</span>\n" +
        "                                    </td>\n" +
        "                                    <td class=\"table__td\">"+order.CustomerName+"</td>\n" +
        "                                    <td class=\"table__td\"><span>$"+order.Total+"</span>\n" +
        "                                    </td>\n" +
        "                                    <td class=\"table__td text-nowrap\"><span class=\"text-grey\">"+order.Date+"</span></td>\n" +
        "                                    <td class=\"d-none d-sm-table-cell table__td\">\n" +
        "                                        <div class=\"table__status\"><span class=\"table__status-icon color-"+tempcolor+"\"></span> "+order.Status+"</div>\n" +
        "                                    </td>\n" +
        "                                    <td class=\"table__td table__actions\">\n" +
        "                                        <div class=\"items-more\">\n" +
        "                                            <button item-value=\""+order.Id+"\" class=\"items-more__button btn-view\">\n" +
        "                                                    <svg class=\"icon-icon-view\">\n" +
        "                                                    <use xlink:href=\"#icon-view\"></use>\n" +
        "                                                    </svg></a>\n" +
        "                                            </button> \n" +
        "                                        </div>\n" +
        "                                    </td>\n" +
        "                                </tr>";
    return content;
}
var listtemp = [];
var listJSON=[];
var ajaxorder = $.ajax({
    async:true,
    url: '/admin/api-order',
    type: 'GET',
    success: function(data, status) {
        listJSON = data;
    }
});

function Load(){
    ajaxorder.done(()=>{
        RenderLoad();
        Init();
        document.getElementById("number-order").innerHTML="("+listJSON.length+")";
    });
    // for(let i =0 ;i<305;i++){
    //     listJSON[i]=order1;
    // }
    // RenderLoad();
    // Init();
    // document.getElementById("number-order").innerHTML="("+listJSON.length+")";
}


function viewcustom(id){
    window.location.href = "/admin/editorder?Id="+id;
}


function RenderLoad(){

    for(let i=0;i<listJSON.length;i++){
        listtemp[i] =Render(listJSON[i]);
    }
}

function phantrang(index){
    let len = listtemp.length;
    let max =  curent_page * num;
    let indexitem = max-num;
    let du = len % num;

    if(max_page_num == curent_page)
        if(du != 0)
            max = max - num + du;


    order_table.innerHTML= "";
    if(listtemp.length!=0){
        for(indexitem;indexitem<max;indexitem++)
        {
            order_table.innerHTML+=listtemp[indexitem];
        }
    }


    listview = document.getElementsByClassName("btn-view");
    loadAjax(listview,viewcustom);
    show_info();

}

function Init(){
    max_page_num = Math.ceil(listtemp.length/num);
    displayPagination();
    pagination_first.children[0].innerHTML=1;
    pagination_last.children[0].innerHTML=max_page_num;
    pagination_last.setAttribute("data-value",max_page_num);
    curent_page =1;
    SetNum(1);
    phantrang(1);
    setActiveNum();


}

function SetNum(page_num){

    if(page_num <= 3){
        pagination_2.children[0].innerHTML= 2;
        pagination_3.children[0].innerHTML =3;
        pagination_4.children[0].innerHTML =4;

        pagination_2.setAttribute("data-value",2);
        pagination_3.setAttribute("data-value",3);
        pagination_4.setAttribute("data-value",4);
    }
    else if( max_page_num - page_num < 3){
        pagination_2.children[0].innerHTML= Number(max_page_num) -3;
        pagination_3.children[0].innerHTML = Number(max_page_num) -2 ;
        pagination_4.children[0].innerHTML = Number(max_page_num) - 1;

        pagination_2.setAttribute("data-value",Number(max_page_num) -3);
        pagination_3.setAttribute("data-value",Number(max_page_num) -2);
        pagination_4.setAttribute("data-value",Number(max_page_num) -1);
    }

    else{
        pagination_2.children[0].innerHTML= Number(page_num) -1;
        pagination_3.children[0].innerHTML = Number(page_num) ;
        pagination_4.children[0].innerHTML = Number(page_num) +1;

        pagination_2.setAttribute("data-value",Number(page_num) -1);
        pagination_3.setAttribute("data-value",Number(page_num));
        pagination_4.setAttribute("data-value",Number(page_num) +1);
    }

    if( max_page_num - page_num < 3)
        pagination_space_2.style.display ="none";
    else
        pagination_space_2.style.display ="flex";

    if(page_num <= 3)
        pagination_space_1.style.display ="none";
    else
        pagination_space_1.style.display ="flex";

    if(max_page_num == page_num)
        pagination_next.style.display ="none";
    else
        pagination_next.style.display ="flex";

    if(page_num == 1)
        pagination_previous.style.display ="none";
    else
        pagination_previous.style.display ="flex";

    setActiveNum();

}
function displayPagination() {
    if(max_page_num <= 4)
        pagination_4.style.display = "none";
    else
        pagination_4.style.display = "flex";

    if(max_page_num <= 3)
        pagination_3.style.display = "none";
    else
        pagination_3.style.display = "flex";

    if(max_page_num <= 2)
        pagination_2.style.display = "none";
    else
        pagination_2.style.display = "flex";

    if(max_page_num <= 1)
        pagination_last.style.display = "none";
    else
        pagination_last.style.display = "flex";

}





function setActiveNum(){
    if (pagination_2.classList.contains('active')) {
        pagination_2.classList.remove('active');
    }
    if (pagination_4.classList.contains('active')) {
        pagination_4.classList.remove('active');
    }
    if (pagination_first.classList.contains('active')) {
        pagination_first.classList.remove('active');
    }
    if (pagination_last.classList.contains('active')) {
        pagination_last.classList.remove('active');
    }
    if (pagination_3.classList.contains('active')) {
        pagination_3.classList.remove('active');
    }

    if(curent_page == 1)
        pagination_first.classList.add('active');
    else if(curent_page == max_page_num)
        pagination_last.classList.add('active');
    else if(pagination_3.getAttribute("data-value") == curent_page)
        pagination_3.classList.add('active');
    else if(pagination_2.getAttribute("data-value") == curent_page)
        pagination_2.classList.add('active');
    else if(pagination_4.getAttribute("data-value") == curent_page)
        pagination_4.classList.add('active');

}

Array.prototype.forEach.call(listshow1, element=>{
    element.addEventListener("click",()=>{
        num = element.getAttribute("data-value");
        Init();
    })
});

function loadAjax(list,func){
    Array.prototype.forEach.call(list, element=>{
        element.addEventListener("click",()=>{
            id = element.getAttribute("item-value");
            func(id);
        })
    });
}


Array.prototype.forEach.call(list_pagination, element=>{
    element.addEventListener("click",()=>{
        let page_num = element.getAttribute("data-value");
        curent_page = Number(page_num);
        SetNum(page_num);
        phantrang();
    })
});

pagination_previous.addEventListener("click",()=>{
    curent_page = Number(curent_page) - 1;
    SetNum(curent_page);
    phantrang();
});

pagination_next.addEventListener("click",()=>{
    curent_page = Number(curent_page) + 1;
    SetNum(curent_page);
    phantrang();
});
function  show_info(){
    let len = listtemp.length;
    let max =  curent_page * num;
    let min = max-num;
    let du = len % num;

    if(max_page_num == curent_page)
        if(du != 0)
            max = max - num + du;


    let temp ="<span class=\"d-none d-sm-inline-block\" >Showing</span> "+min+" to "+max+"  <span class=\"d-none d-sm-inline-block\">of "+len+" items</span>"
    show_infos.innerHTML = temp;
}

function RenderSearch(key){
    let tempindex  =0;
    listtemp =[];
    for(let i=0;i<listJSON.length;i++){
        let tempstring = listJSON[i].Id+"|"+listJSON[i].CustomerName+"|"+listJSON[i].Date+"|";
        if(tempstring.toUpperCase().includes(key.toUpperCase())){
            listtemp[tempindex] =Render(listJSON[i]);
            tempindex++;
        }
    }
    Init();
    document.getElementById("number-order").innerHTML="("+listtemp.length+")";
}

function RenderActive(key){
    let tempindex  =0;
    listtemp =[];
    for(let i=0;i<listJSON.length;i++){
        if(listJSON[i].Status == key){
                listtemp[tempindex] =Render(listJSON[i]);
            tempindex++;
        }
    }
    Init();
    document.getElementById("number-order").innerHTML="("+listtemp.length+")";
}

function RenderAllStatus(){
    RenderLoad();
    Init();
    document.getElementById("number-order").innerHTML="("+listtemp.length+")";
}





function Search(){
    let temp = document.getElementById("search-box").value;
    RenderSearch(temp);
}

document.getElementById("all-status").addEventListener("click",()=>{
    RenderAllStatus();
});

Load();








