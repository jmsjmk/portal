$(function () {
    var user = {
        table: function () {
            $('#search').DataTable({
                bPaginate: true,// 分页按钮
                lengthChange: true,  //修改每页数量
                searching: true,  //搜索功能
                order: [[0, 'DESC']],   //默认排序字段
                info: true,   //显示数据信息 第几页,总共几页等等
                autoWidth: false, //是否固定宽度
                bProcessing: false,    //服务器等等提示
                bServerSide: true, //表示从服务器获取
                lengthMenu: [[10, 15, 25, 50], [10, 15, 25, 50]],    //每页数据管理
                bDestroy: true,
                bSortCellsTop: true,
                bSortClasses: true,
                ajax: {
                    url: '/user/api/list'
                },
                columns: [
                    {data: 'id'},
                    {data: 'userName'},
                    {data: 'email'},
                    {data: 'displayName'},
                    {data: 'status'},
                ],
                select: true,
                language: {
                    url: '/json/dataTable.oLanguage.json'
                },
                fnInitComplete: function () {
                    if ($('#search_wrapper div.btn-group').length > 0) return;
                    var buttons = '<div class="col-sm-8"><div class="dt-buttons btn-group">' +
                        '<button class="btn btn-primary buttons-created" data-toggle="modal" data-target="#inputModal">' +
                        '<span>创建用户</span></button>' +
                        '<button class="btn btn-primary buttons-refresh" autocomplete="off" tabindex="0" aria-controls="example">' +
                        '<i class="fa fa-repeat"></i></button>' +
                        '</div></div>';
                    $('#search_wrapper div.row:first').prepend(buttons);
                    $('#search_length').parent().removeClass('col-sm-6').addClass('col-sm-1');
                    $('#search_filter').parent().removeClass('col-sm-6').addClass('col-sm-3');
                },
                fnRowCallback: function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                    $(nRow).attr('data-id', aData['id']);
                }
            });
        },
        trSelected: function () {
            $('.content').delegate('table > tbody > tr', 'click', function () {
                var _clz = 'selected';
                if ($(this).hasClass(_clz)) {
                    $(this).removeClass(_clz)
                } else {
                    $(this).parent().find('tr.' + _clz).removeClass('selected');
                    $(this).addClass(_clz);
                }
            });
        },
        refreshTable: function () {
            $('.content').delegate('.buttons-refresh', 'click', function () {
                $("#search").dataTable().fnDraw(true);
            })
        },
        insert: function () {
            var options = {
                type: "POST",
                dataType: "json",
                success: function (json) {// 表单提交成功回调函数
                    $('#inputModal').modal('hide');
                    $('#inputModal form').resetForm();
                },
                error: function (err) {
                    alert("表单提交异常！" + err.msg);
                }
            };
            $("#inputModal form").ajaxForm(options);
        }
    };
    user.table();
    user.trSelected();
    user.refreshTable();
    user.insert();
});