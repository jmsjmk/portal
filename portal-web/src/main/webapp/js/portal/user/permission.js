$(function () {
    var permission = {
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
                    url: '/permission/api/list'
                },
                columns: [
                    {data: 'id'},
                    {data: 'name'},
                    {data: 'note'},
                    {data: 'uniqueKey'},
                    {data: 'parentId'},
                    {data: 'link'},
                ],
                select: true,
                language: {
                    url: '/json/dataTable.oLanguage.json'
                },
                fnInitComplete: function () {
                    if ($('#search_wrapper div.btn-group').length > 0) return;
                    var buttons = '<div class="col-sm-8"><div class="dt-buttons btn-group">' +
                        '<button class="btn btn-primary buttons-create" data-action="create"  data-action="new" data-toggle="modal" data-target="#inputModal">' +
                        '<span>New</span></button>' +
                        '<button class="btn btn-primary buttons-p2r" data-toggle="modal" data-target="#setModal" disabled>' +
                        '<span>P2R</span></button>' +
                        '<button class="btn btn-primary buttons-edit" data-action="edit" data-toggle="modal" data-target="#inputModal" disabled>' +
                        '<span>Edit</span></button>' +
                        '<button class="btn btn-primary buttons-delete" disabled>' +
                        '<span>Delete</span></button>' +
                        '<button class="btn btn-primary buttons-refresh" autocomplete="off" tabindex="0" aria-controls="example">' +
                        '<i class="fa fa-repeat"></i></button>' +
                        '</div></div>';
                    $('#search_wrapper div.row:first').prepend(buttons);
                    $('#search_length').parent().removeClass('col-sm-6').addClass('col-sm-1');
                    $('#search_filter').parent().removeClass('col-sm-6').addClass('col-sm-3');
                },
                fnDrawCallback: function () {
                    $('.buttons-p2r').attr('disabled', 'disabled');
                    $('.buttons-edit').attr('disabled', 'disabled');
                    // $('.buttons-delete').addClass('disabled');
                },
                fnRowCallback: function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                    $(nRow).attr('data-id', aData['id']);
                },
                aoColumnDefs: [
                    {"sWidth": "40px", "aTargets": [0]},
                    {"sWidth": "90px", "aTargets": [4]},
                ]
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
                var _tr = $(this).parent().find('tr.' + _clz);
                if (_tr.length > 0) {
                    $('.buttons-p2r').removeAttr('disabled');
                    $('.buttons-edit').removeAttr('disabled');
                    // $('.buttons-delete').removeClass('disabled');
                } else {
                    $('.buttons-p2r').attr('disabled', 'disabled');
                    $('.buttons-edit').attr('disabled', 'disabled');
                    // $('.buttons-delete').addClass('disabled');
                }
            });
        },
        refreshTable: function () {
            $('.content').delegate('.buttons-refresh', 'click', function () {
                $("#search").dataTable().fnDraw(true);
            })
        },
        ajaxForm: function () {
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
        },
        permission2Role: function () {
            var options = {
                type: "POST",
                dataType: "json",
                success: function (json) {// 表单提交成功回调函数
                    $('#setModal').modal('hide');
                },
                error: function (err) {
                    alert("表单提交异常！" + err.msg);
                }
            };
            $("#setModal form").ajaxForm(options);
        },
        closeModal: function () {
            $('.modal').on('hide.bs.modal', function () {
                $(this).find('form').resetForm();
                $(this).find('input').val('');

            });
        },
        openModal: function () {
            $('#inputModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget);// Button that triggered the modal
                var action = button.data('action');
                if (action == 'edit') {
                    var pid = $('tr.selected').attr('data-id');
                    $('#inputModal form').attr('action', '/permission/api/update')
                    $('#permissionId').val(pid);
                    $.ajax({
                        url: '/permission/api/' + pid,
                        type: 'GET',
                        dataType: 'JSON',
                        success: function (data) {
                            var o = data.data;
                            $('#name').val(o.name);
                            $('#note').val(o.note);
                            $('#code').val(o.code);
                            $('#uniqueKey').val(o.uniqueKey);
                            $('#parentId').val(o.parentId);
                            $('#link').val(o.link);
                            $('#type').val(o.type);
                            $('#icon').val(o.icon);
                            $('#weight').val(o.weight);
                            $('#id').val(o.id);
                        },
                        error: function (data) {
                        }
                    });
                } else if (action == 'create') {
                    $('#inputModal form').attr('action', '/permission/api/insert')
                }
            });
        }
    };
    permission.table();
    permission.trSelected();
    permission.refreshTable();
    permission.ajaxForm();
    permission.permission2Role();
    permission.openModal();
    permission.closeModal();

    $('.content').delegate('.buttons-p2r', 'click', function () {
        if ($(this).attr('disabled')) {
            return;
        }
        var pid = $('tr.selected').attr('data-id');
        $('#pid').val(pid);
    });
});