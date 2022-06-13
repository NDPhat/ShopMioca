function checked (classname)
{
    var arr=document.getElementsByClassName(classname)
    for (var i = 0; i < 5; i++) {
        if(arr[i].checked)
        {
            //arr[i].value=i;
            return true;
        }
    }
}