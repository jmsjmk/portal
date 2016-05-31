(function ($) {
    $.fn.model = function (options) {
        //columns : [{data : '',type,''}]
        var settings = {
            title: "Modal title",
            saveText: "Save changes",
            columns: [],
            ajax: ''
        };
        var opts = $.extend(settings, options);
        this.each(function () {
            initModel();
        });
        var _this = $(this);

        function initModel() {
            var mh = [];
            mh.push('<div class="modal fade" id="cModal" tabindex="-1" role="dialog" aria-labelledby="cModalLabel">')
            mh.push('<div class="modal-dialog" role="document">');
            mh.push('<div class="modal-content">');
            mh.push('<div class="modal-header">');
            mh.push('<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
            mh.push('<h4 class="modal-title" id="cModalLabel">' + opts.title + '</h4>');
            mh.push('</div>');
            mh.push('<div class="modal-body">');
            //body
            $.each(opts.columns, function (d) {
                mh.push('');
                mh.push('');
                mh.push('');
            })
            mh.push('</div>');
            mh.push('<div class="modal-footer">');
            mh.push('<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>');
            mh.push('<button type="button" class="btn btn-primary">' + opts.saveText + '</button>');
            mh.push('</div></div></div></div>');
        }
    }
})(jQuery);