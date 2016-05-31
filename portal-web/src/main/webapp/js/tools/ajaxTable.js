(function ($) {
    $.fn.ajaxTable = function (options) {
        var settings = {
            url: '',
            pageDom: '.pagination',
            paging: true,
            curPage: 1,
            pageSize: 20,
            columns: [],
            data: {}
        }
        var opts = $.extend(settings, options);
        this.each(function () {
            ajax(opts.curPage);
        });
        var _this = $(this);

        function ajax(curPage) {
            var param = opts.data || {};
            param.curPage = curPage;
            param.pageSize = opts.pageSize;
            $.ajax({
                url: opts.url,
                data: param,
                dataType: 'JSON',
                success: function (obj) {
                    if (!obj)
                        return;
                    var data = obj.data;
                    var str = [];
                    $.each(data.items, function (i, o) {
                        str.push('<tr>');
                        $.each(opts.columns, function (i, c) {
                            str.push('<td>');
                            if (o[c] instanceof Array) {
                                $.each(o[c], function (_i, _o) {
                                    str.push(_o);
                                    str.push('&nbsp;&nbsp;');
                                });
                            } else {
                                str.push(o[c]);
                            }
                            str.push('</td>');
                        });
                        str.push('</tr>');
                    })
                    _this.find('tbody').html(str.join(''));
                    if (opts.paging) {
                        pages(data);
                    }
                }
            });
        }

        function pageSection(curPage, totalPage) {
            var endPage = 10;
            var page = [1, endPage];
            if (totalPage > endPage) {
                if (curPage > 5) {
                    if ((totalPage - curPage) >= 5) {
                        page = [curPage - 4, curPage + 5];
                    } else {
                        page = [totalPage - endPage + 1, totalPage];
                    }
                }
            } else {
                page = [1, totalPage];
            }
            return page;
        }

        function pages(obj) {
            if (obj.totalPage <= 1) {
                return false;
            }
            var str = [];
            var curPage = obj.curPage;
            var totalPage = obj.totalPage;
            str.push('<li><a href="javascript:;" n="1"');
            if (curPage == 1) {
                str.push(' class="not-allowed" ');
            }
            str.push('>First</a></li>');
            str.push('<li><a href="javascript:;" n="' + (curPage - 1) + '"');
            if (curPage <= 1) {
                str.push(' class="not-allowed" ');
            }
            str.push('>Prev</a></li>');
            var section = pageSection(curPage, totalPage);
            for (var i = section[0]; i <= section[1]; i++) {
                str.push('<li');
                if (i == curPage) {
                    str.push(' class="active" ');
                }
                str.push('><a href="javascript:;" n="');
                str.push(i);
                str.push('">');
                str.push(i);
                str.push('</a></li>');
            }
            str.push('<li><a href="javascript:;"  n="' + (curPage + 1) + '"');
            if (curPage >= totalPage) {
                str.push(' class="not-allowed" ');
            }
            str.push('>Next</a></li>');
            str.push('<li><a href="javascript:;"  n="' + obj.totalPage + '"');
            if (curPage == totalPage) {
                str.push(' class="not-allowed" ');
            }
            str.push('>Last</a></li>');
            $(opts.pageDom).html(str.join(''));
        }

        $(opts.pageDom).delegate(
            'li a',
            'click',
            function () {
                if ($(this).hasClass('not-allowed')
                    || $(this).parent().hasClass('active')) {
                    return false;
                }
                ajax($(this).attr('n'));
                $('html,body').scrollTop($('#a_content').offset().top);
            });
    }
})(jQuery);