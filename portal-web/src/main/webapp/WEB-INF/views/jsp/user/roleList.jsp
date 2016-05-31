<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body class="hold-transition skin-blue sidebar-mini">
<link rel="stylesheet" href="/css/bootstrap/dataTables.bootstrap.css">
<style>
    table > tbody > tr.odd.selected {
        background-color: #B0BED9;
    }

    table > tbody > tr.selected {
        background-color: #B0BED9;
    }
</style>
<section class="content-header">
    <h1>
        Role
        <small>List</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>角色管理</a></li>
        <li class="active">Role List</li>
    </ol>
</section>
<section class="content">
    <div class="box">
        <div class="box-header">
            <h3 class="box-title">角色列表</h3>
        </div>
        <div class="box-body">
            <table id="search" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>角色名</th>
                    <th>角色说明</th>
                    <th>角色Key</th>
                    <th>状态</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="5">Loading data from server</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</section>
<!-- Modal -->
<div class="modal fade bs-example-modal-sm" id="inputModal" data-backdrop="static" role="dialog"
     aria-labelledby="inputLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <form action="/role/api/insert" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="inputLabel">新增角色</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="name" name="name" placeholder="Role name">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="note" name="note" placeholder="note">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="uniqueKey" name="uniqueKey" placeholder="uniqueKey">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade bs-example-modal-sm" id="setModal" data-backdrop="static" role="dialog"
     aria-labelledby="setLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <form action="/role/api/role2GU" method="post">
                <input type="hidden" id="rid" name="rid"/>
                <input type="hidden" id="action" name="action"/>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="setLabel"></h4>
                </div>
                <div class="modal-body">
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="paramId" name="paramId" placeholder="paramId">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/js/jQuery/jquery.dataTables.min.js"></script>
<script src="/js/bootstrap/dataTables.bootstrap.min.js"></script>
<script src="/js/jQuery/jquery.form.js"></script>
<script src="/js/portal/user/role.js"></script>
</body>
</html>