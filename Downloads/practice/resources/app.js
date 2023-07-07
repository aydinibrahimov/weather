$(document).ready(function a(callback) {

    $('#consumer1').on("click", function (e) {
        var linker = $(this).val();
        e.preventDefault()
        $.ajax(
            {
                url: "resources/users.json",
                type: "GET",
                dataType: 'json',
                success: function (data) {


                    var obj = '';



                    for (var i = 0; i < data.length; i++) {
                        if (data[i].property != 'Biznes') {
                            continue;
                        } else {
                            obj += '<tr id="' + data[i].id + '">';

                            obj += '<td>' + data[i].id + '</td>';
                            obj += '<td>' + data[i].name + '</td>';
                            obj += '<td>' + data[i].surname + '</td>';
                            obj += '<td>' + data[i].phone + '</td>';
                            obj += '<td>' + data[i].email + '</td>';
                            obj += '<td>' + data[i].insType + '</td>';
                            obj += '<td>' + data[i].insAmount + '</td>';
                            obj += '<td>' + data[i].property + '</td>';


                            obj += '</tr>'
                        }

                    }

                    $('#gridTable').html(obj);
                    callback();

                },
                error: function (error) {
                    console.log(error)
                }
            });

    })


    $('#consumer2').on("click", function (e) {
        e.preventDefault()
        $.ajax(
            {
                url: "resources/users.json",
                type: "GET",
                dataType: 'json',
                success: function (data) {
                    var obj = '';

                    for (var i = 0; i < data.length; i++) {
                        if (data[i].property != 'Fərdi') {
                            continue;
                        } else {
                            obj += '<tr id= "' + data[i].id + '">';

                            obj += '<td>' + data[i].id + '</td>';
                            obj += '<td>' + data[i].name + '</td>';
                            obj += '<td>' + data[i].surname + '</td>';
                            obj += '<td>' + data[i].phone + '</td>';
                            obj += '<td>' + data[i].email + '</td>';
                            obj += '<td>' + data[i].insType + '</td>';
                            obj += '<td>' + data[i].insAmount + '</td>';
                            obj += '<td>' + data[i].property + '</td>';


                            obj += '</tr>'
                        }

                    }


                    $('#gridTable').html(obj);

                },
                error: function (error) {
                    console.log(error)
                }
            });
    })


    $('#consumer3').on("click", function (e) {
        e.preventDefault()
       $.ajax(
            {
                url: "resources/users.json",
                type: "GET",
                dataType: 'json',
                success: function (data) {
                    $('#gridTable').jqGrid({
                        data: data,
                        colModel: [
                            {name: 'id'},
                            {name: data.name},
                            {name: data.surname},
                            {name: data.phone },
                            {name: data.email},
                            {name: data.insType},
                            {name: data.insAmount},
                            {name: data.property},
                        ],
                        autowidth: true,
                        height: 'auto',
                        rowNum: 10,
                        pager: '#gridPager'
                    });

                },
                error: function (error) {
                    console.log(error)
                }
            });
    })




    $(document).ready(function () {
        $(document).on("click", "tr", (function () {

            let id = $(this).find('td:first').text();
            details(id);



        })
        )
    })
    function details(id) {
        $.ajax({
            url: "resources/users.json",
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                $(data).each(function (key, value) {
                    if (id == data[key].id) {
                        $('#detailslist').html(
                            `<li><h5>Id</h5><span>${value.id}</span></li>
                            <li><h5>Ad</h5> <span>${value.name}</span></li>     
                            <li><h5>Soyad</h5> <span>${value.surname}</span></li>
                            <li><h5>Sığorta növü</h5> <span>${value.insType}</span></li>
                            <li><h5>Sığorta Mülkiyyəti</h5> <span>${value.property}</span></li>
                            <li><h5>Sığorta dəyəri</h5> <span>${value.insAmount}</span></li>
                            <li><h5>Email</h5> <span>${value.email}</span></li>`
                        );

                    }
                });
            },
            error: function (error) {
                console.log(error);
            }
        });

    }


})
$('#toolbarExcel').on('click', function () {
    var table2excel = new Table2Excel();
    table2excel.export(document.querySelectorAll("#gridTable"));

})







