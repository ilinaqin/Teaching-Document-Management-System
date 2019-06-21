var did=""
var place=""
var row=""
var colum=""
function Click(str,plac){
	did = str;
	place=plac;
	
}
webpackJsonp([7], {
    0 : function(e, t, a) {
        "use strict";
        function n(e, t) {
        	row = e;
        	colum = t;
            return '<span value="' + e + '" class="ticket" data-row-id="' + e + '" data-column-id="' + t + '" data-index="' + e + "-" + t + '">' + e + "排" + t + "列 </span>"
        }
        function s(e) {
            return e.hasClass("lover-left") || e.hasClass("lover-right")
        }
        function r(e) {
            return e.hasClass("lover-left") ? e.next(".lover-right") : e.prev(".lover-left")
        }
        function o(e) {
            var t = e.data(),
            a = t.rowId,            
            s = t.columnId,
            r = t.no,
            o = t.st;            
            B.push({
                rowId: a,
                columnId: s,
                seatNo: r,
                type: o
            }),
            k.append(n(a, s)),
            e.removeClass("selectable").addClass("selected")
        }
        function c(e) {
            return B.length >= P ? d("只能选择" + P + "个位置进行归档") : (x.hide(), g.show(), o(e), s(e) && o(r(e)), q.removeClass("disable"), void j.text(A[B.length].price.replace("元", "")))
        }
        function i(e) {
            var t = e.data(),
            a = t.rowId,
            n = t.columnId,
            s = a + "-" + n;
            B = B.filter(function(e) {
                return ! (e.rowId === a && e.columnId === n)
            }),
            $('.ticket[data-index="' + s + '"]').remove(),
            j.text(A[B.length].price.replace("元", "")),
            e.removeClass("selected").addClass("selectable")
        }
        function l(e) {
            i(e),
            s(e) && i(r(e)),
            0 === B.length && (x.show(), g.hide(), q.addClass("disable"))
        }
        function d(e, t) {
            J.find(".icon").removeClass("ox xox"),
            t && J.find(".icon").addClass(t),
            J.find(".tip").text(e),
            J.show()
        }
        function u(e) {
            return e.hasClass("selectable")
        }
        function h(e) {
            return e.hasClass("selected")
        }
        function f(e, t) {
            function a(t) {
                var a = e ? t.prev() : t.next();
                return 0 === a.length || a.hasClass("empty") ? $() : a
            }
            return t ? a(t) : a
        }
       /* function p(e, t, a) {
            var n = t(e);
            if (u(n)) {
                if (n = t(n), u(n)) return ! 1;
                if (h(n)) return d("座位中间不要留空", "xox"),
                !0;
                if (!u(n)) {
                    var s = P;
                    for (n = e; s--;) if (n = a(n), !h(n)) {
                        var r = u(n);
                        return r && d("座位旁边不要留空", "ox"),
                        r
                    }
                }
            }
        }*/
        function m() {
            for (var e = $(".seats-wrapper").find(".selected"), t = e.length, a = f(!0), n = f(!1), s = 0; s < t; ++s) {
                var r = e.eq(s);
                if (p(r, a, n) || p(r, n, a)) return ! 1
            }
            return ! 0
        }
        function v() {
            var e = N.data(),
            t = e.sectionId,
            a = e.sectionName,
            n = e.seqNo,
            s = {
                count: B.length,
                list: B
            },
            r = {
                sectionId: t,
                sectionName: a,
                seqNo: n,
                seats: JSON.stringify(s)
            };
            $.post("/ajax/createOrder", r,
            function(e) {
                e.data && e.data.data ? location.href = "/order/confirm/" + e.data.data.id: d(e.error ? e.error.message: "抱歉，您的座位已被其他用户选中，请重新选择")
            })
        }
        function w(e, t) {
            e.text("重新获取(" + t + ")").addClass("disable"),
            F = setInterval(function() {
                t--,
                e.text("重新获取(" + t + ")"),
                0 === t && (clearInterval(F), e.text("重新获取").removeClass("disable"))
            },
            1e3)
        }
        function y(e) {
            $.post({
                url: "/ajax/mobileLogin",
                data: {
                    phone: E || 1,
                    code: O.val() || 1
                },
                success: function(t) {
                    t.success && e ? e() : t.error && d(t.error.message)
                }
            })
        }
        function C(e) {
            return /^1[3|4|5|8]\d{9}$/.test(e)
        }
        function I() {
            return H.children(".input-captcha").val()
        }
        function b() {
            $(".captcha-pic").attr("src", "http://www.meituan.com/account/appcaptcha?uuid=" + system.uuid + "&captchaHash=" + +new Date)
        }
        a(1260);
        var x = $(".no-ticket"),
        g = $(".has-ticket"),
        k = $(".ticket-container"),
        N = $(".seats-block"),
        _ = $(".screen"),
        j = $(".total-price .price"),
        q = $(".confirm-btn"),
        J = $(".modal-container"),
        L = $(".input-phone"),
        O = $(".input-code"),
        D = $(".send-code"),
        H = $(".captcha"),
        P = +k.data("limit"),
        S = _.width(),
        z = $(".seats-wrapper").width(),
        A = system.seatsPrice,
        B = [],
        E = void 0,
        F = void 0; !
        function() {
            system.error && J.show(),
            system.remind && system.remind.length > 0 && J.show()
        } (),
        A && (A[0] = {
            price: "0元"
        }),
        system.user || $(".login-form input").placeholder(),
        J.on("click", ".ok-btn",
        function() {
            J.hide(),
            system.error && (system.cinemaId ? location.href = "http://www.maoyan.com/cinema/" + system.cinemaId: system.poiId ? location.href = "http://www.maoyan.com/cshop/" + system.poiId: location.href = "http://www.maoyan.com")
        }),
        $(".ticket-container").on("click", ".ticket",
        function() {
            var e = $(this).data(),
            t = e.rowId,
            a = e.columnId;
            $(".seat").each(function() {
                var e = $(this);
                if (e.data("columnId") === a && e.data("rowId") === t) return l(e),
                !1
            })
        }),
        function() {
            var e = z / 2;
            $(".screen-container").css("left", e - S / 2)
        } (),
        $(".seats-block").on("click", ".seat",
        function() {
            var e = $(this);
            if (!e.hasClass("empty") && !e.hasClass("sold")) return u(e) ? c(e) : h(e) ? l(e) : void 0
        }),
        $(".change-captcha").on("click", b),
        L.on("input",
        function() {
            var e = $(this);
            E = e.val(),
            C(E) ? (F && (clearInterval(F), D.text("重新获取")), D.removeClass("disable")) : D.addClass("disable")
        }),
  /*      D.on("click",
        function() {
            var e = {
                phone: E
            };
            "block" === H.css("display") && (e.captcha = I()),
            $(this).hasClass("disable") || $.post({
                url: "/ajax/mobileLogin",
                data: e,
                success: function(e) {
                    e.error ? ("user_err_captcha_error" === e.error.type && b(), "user_err_need_captcha" === e.error.type && H.show(), d(e.error.message)) : w(D, 60)
                }
            })
        }),*/
        $(".confirm-btn").on("click",
        function(e) {        	        	
           // if (!$(this).hasClass("disable") && m()) return system.user ? void v() : E && C(E) ? 0 === O.val().length ? d("请输入手机验证码") : y(v) : d("请输入11位正确的手机号码")
        
        	var url = '${ pageContext.request.contextPath }/secdocument_setplace.action';
     	    $.ajax({
     	        type: "post",
     	        url: url,
     	       data: {"did":parseInt(did),"place":place+'存档室-'+row + '排' + colum + '列'}, 
     	        cache: false,
     	        async : true,//ajax为异步
     	         // dataType: "json",                                            
     	        
     	        
     	       success: function ()
     	        {  alert("保存成功！")
     	    	  // window.location.reload(); //刷新当前页面 
     	    	  window.location.href='${ pageContext.request.contextPath }/secdocument_secUnplace.action';
     	        }, 
     	         error:function (XMLHttpRequest, textStatus, errorThrown) {      
     	            alert("请求失败！");
     	        } 
     	     });
        }),
        a(1261)
    },
    1261 : 1283
});