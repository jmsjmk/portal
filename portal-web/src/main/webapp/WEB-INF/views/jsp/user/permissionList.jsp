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
        Permission
        <small>List</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>权限管理</a></li>
        <li class="active">Permission List</li>
    </ol>
</section>
<section class="content">
    <div class="box">
        <div class="box-header">
            <h3 class="box-title">权限列表</h3>
        </div>
        <div class="box-body">
            <table id="search" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>权限名</th>
                    <th>权限说明</th>
                    <th>权限Key</th>
                    <th>上级权限ID</th>
                    <th>Link</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td colspan="10">Loading data from server</td>
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
            <form action="" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="inputLabel">创建权限</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="permissionId" name="id"/>
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="name" name="name" placeholder="Permission name">
                        <span class="fa fa-edit form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="note" name="note" placeholder="Note">
                        <span class="fa fa-edit form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="code" name="code" placeholder="Code">
                        <span class="fa fa-edit form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="uniqueKey" name="uniqueKey" placeholder="UniqueKey">
                        <span class="fa fa-edit form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="parentId" name="parentId" placeholder="parentId">
                        <span class="fa fa-edit form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="link" name="link" placeholder="Link">
                        <span class="fa fa-edit form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="type" name="type" placeholder="Type">
                        <span class="fa fa-edit form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="icon" name="icon" placeholder="Icon">
                        <span class="fa fa-edit form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="weight" name="weight" placeholder="Weight">
                        <span class="fa fa-edit form-control-feedback"></span>
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
            <form action="/permission/api/permission2Role" method="post">
                <input type="hidden" id="pid" name="pid"/>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="setLabel">Permission2Role</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="rid" name="rid" placeholder="RoleId">
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
<script src="/js/portal/user/permission.js"></script>
</body>
</html>