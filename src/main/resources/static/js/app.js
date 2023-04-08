function submit_logout(){
    let form = document.getElementById("logout_btn");
    form.submit();
}

$("#rowAdder").click(function () {
    let inx = $('div[id^=row]').length;
    let list = document.getElementById("unitsDet").children;
    let rowIngredientAdd = '<div id="row" class="row align-items-center">\n' +
        '    <div class="form-group col-sm-auto mb-3">\n' +
        '        <button class="btn btn-outline-danger"\n' +
        '                id="DeleteRow" type="button">\n' +
        '            <i class="bi bi-trash"></i>X\n' +
        '        </button>\n' +
        '    </div>\n' +
        '    <div class="form-group col-sm align-self-center">\n' +
        '        <input \n' +
        '               name="ingredientList[' + inx + '].productName"\n' +
        '               min="1"\n' +
        '               type="text" maxlength="20"\n' +
        '               class="form-control"\n' +
        '               placeholder="Product name"\n' +
        '               onfocusout="funcProductName(this)" required/>\n' +
        '    </div>\n' +
        '    <div class="form-group col-sm align-self-center">\n' +
        '        <input \n' +
        '               name="ingredientList[' + inx + '].quantity"\n' +
        '               min="1"\n' +
        '               type="text" maxlength="9"\n' +
        '               class="form-control"\n' +
        '               placeholder="Quantity"\n' +
        '               required\n' +
        '               />\n' +
        '    </div>\n' +
        '    <div class="form-group col-sm mb-3">\n' +
        '        <select name="ingredientList[' + inx + '].measureUnit">\n' +

        optionsAppend(list) +

        '        </select>\n' +
        '    </div>\n' +
        '</div>';

    if (inx < 20) {
        $('#ingredientsInput').append(rowIngredientAdd);
    }
    $('select').niceSelect()
});

$("body").on("click", "#DeleteRow", function () {
    $(this).parents("#row").remove();

    $("input[name*='.productName']").each(function(index, Element){
        //rename productName
        $(this).attr('name', 'ingredientList['+index+'].productName')
    });

    $("input[name*='.quantity']").each(function(index, Element){
        //rename quantity
        $(this).attr('name', 'ingredientList['+index+'].quantity')
    });

    $("select[name*='.measureUnit']").each(function(index, Element){
        //rename measureUnit
        $(this).attr('name', 'ingredientList['+index+'].measureUnit')
    });
})

function optionsAppend(list) {
    let outStr = "";
    for (let ix = 0; ix < list.length; ix++) {
        outStr += `<option>${list[ix].value}</option>`
    }
    return outStr;
}




