<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>101网校</title>
    <style type="text/css">
        /*base*/
        body {
            margin: 0px;
            font-size: 12px;
            padding: 0px;
            min-width: 1200px;
            font-family: '微软雅黑', '宋体';
        }
        div, p, ul, li, input {
            margin: 0px;
            padding: 0px;
            font-size: 14px;
        }
        li {
            list-style: none;
        }
        a {
            text-decoration: none;
            color: #0066cc;
        }
        .t-c {
            text-align: center;
        }
        /*base*/
        /*button*/
        @-webkit-keyframes glowing {
            /* line 8, ../scss/_glow.scss */
            from {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 9, ../scss/_glow.scss */
            50% {
                -webkit-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 10, ../scss/_glow.scss */
            to {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }
        }

        @-khtml-keyframes glowing {
            /* line 14, ../scss/_glow.scss */
            from {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 15, ../scss/_glow.scss */
            50% {
                -webkit-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 16, ../scss/_glow.scss */
            to {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }
        }

        @-moz-keyframes glowing {
            /* line 20, ../scss/_glow.scss */
            from {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 21, ../scss/_glow.scss */
            50% {
                -webkit-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 22, ../scss/_glow.scss */
            to {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }
        }

        @-ms-keyframes glowing {
            /* line 26, ../scss/_glow.scss */
            from {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 27, ../scss/_glow.scss */
            50% {
                -webkit-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 28, ../scss/_glow.scss */
            to {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }
        }

        @-o-keyframes glowing {
            /* line 32, ../scss/_glow.scss */
            from {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 33, ../scss/_glow.scss */
            50% {
                -webkit-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 34, ../scss/_glow.scss */
            to {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }
        }

        @keyframes glowing {
            /* line 38, ../scss/_glow.scss */
            from {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 39, ../scss/_glow.scss */
            50% {
                -webkit-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 16px rgba(44, 154, 219, 0.8), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }

            /* line 40, ../scss/_glow.scss */
            to {
                -webkit-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                -moz-box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 0px 0px rgba(44, 154, 219, 0.3), 0px 1px 2px rgba(0, 0, 0, 0.2);
            }
        }

        /* line 10, ../scss/button.scss */
        .button {
            -webkit-box-shadow: inset 0px 1px 0px rgba(255, 255, 255, 0.5), 0px 1px 2px rgba(0, 0, 0, 0.2);
            -moz-box-shadow: inset 0px 1px 0px rgba(255, 255, 255, 0.5), 0px 1px 2px rgba(0, 0, 0, 0.2);
            box-shadow: inset 0px 1px 0px rgba(255, 255, 255, 0.5), 0px 1px 2px rgba(0, 0, 0, 0.2);
            background-color: #eeeeee;
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #fbfbfb), color-stop(100%, #e1e1e1));
            background: -webkit-linear-gradient(top, #fbfbfb, #e1e1e1);
            background: -moz-linear-gradient(top, #fbfbfb, #e1e1e1);
            background: -o-linear-gradient(top, #fbfbfb, #e1e1e1);
            background: linear-gradient(top, #fbfbfb, #e1e1e1);
            display: -moz-inline-stack;
            display: inline-block;
            vertical-align: middle;
            *vertical-align: auto;
            zoom: 1;
            *display: inline;
            border: 1px solid #d4d4d4;
            height: 32px;
            line-height: 32px;
            padding: 0px 25.6px;
            font-weight: 300;
            font-size: 14px;
            font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
            color: #666666;
            text-shadow: 0 1px 1px white;
            text-decoration: none;
            text-align: center;
            cursor: pointer;
        }
        /* line 29, ../scss/button.scss */
        .button:hover {
            background-color: #eeeeee;
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #ffffff), color-stop(100%, #dcdcdc));
            background: -webkit-linear-gradient(top, #ffffff, #dcdcdc);
            background: -moz-linear-gradient(top, #ffffff, #dcdcdc);
            background: -o-linear-gradient(top, #ffffff, #dcdcdc);
            background: linear-gradient(top, #ffffff, #dcdcdc);
        }
        /* line 33, ../scss/button.scss */
        .button:active {
            -webkit-box-shadow: inset 0px 1px 3px rgba(0, 0, 0, 0.3), 0px 1px 0px white;
            -moz-box-shadow: inset 0px 1px 3px rgba(0, 0, 0, 0.3), 0px 1px 0px white;
            box-shadow: inset 0px 1px 3px rgba(0, 0, 0, 0.3), 0px 1px 0px white;
            text-shadow: 0px 1px 0px rgba(255, 255, 255, 0.4);
            background: #eeeeee;
            color: #bbbbbb;
        }

        /* line 42, ../scss/button.scss */
        input.button, button.button {
            height: 34px;
            cursor: pointer;
        }

        /* line 48, ../scss/button.scss */
        .button-block {
            display: block;
        }

        /* line 57, ../scss/button.scss */
        .button.disabled,
        .button.disabled:hover,
        .button.disabled:active,
        input.button:disabled,
        button.button:disabled {
            -webkit-box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.1);
            -moz-box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.1);
            box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.1);
            background: #EEE;
            border: 1px solid #dddddd;
            text-shadow: 0 1px 1px white;
            color: #CCC;
            cursor: default;
        }

        /* line 67, ../scss/button.scss */
        .button-wrap {
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #e3e3e3), color-stop(100%, #f2f2f2));
            background: -webkit-linear-gradient(top, #e3e3e3, #f2f2f2);
            background: -moz-linear-gradient(top, #e3e3e3, #f2f2f2);
            background: -o-linear-gradient(top, #e3e3e3, #f2f2f2);
            background: linear-gradient(top, #e3e3e3, #f2f2f2);
            -webkit-border-radius: 200px;
            -moz-border-radius: 200px;
            -ms-border-radius: 200px;
            -o-border-radius: 200px;
            border-radius: 200px;
            -webkit-box-shadow: inset 0px 1px 3px rgba(0, 0, 0, 0.04);
            -moz-box-shadow: inset 0px 1px 3px rgba(0, 0, 0, 0.04);
            box-shadow: inset 0px 1px 3px rgba(0, 0, 0, 0.04);
            padding: 10px;
            display: inline-block;
        }

        /* line 118, ../scss/button.scss */
        .button-primary {
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #00b5e5), color-stop(100%, #008db2));
            background: -webkit-linear-gradient(top, #00b5e5, #008db2);
            background: -moz-linear-gradient(top, #00b5e5, #008db2);
            background: -o-linear-gradient(top, #00b5e5, #008db2);
            background: linear-gradient(top, #00b5e5, #008db2);
            background-color: #00a1cb;
            border-color: #007998;
            color: white;
            text-shadow: 0 -1px 1px rgba(0, 40, 50, 0.35);
        }
        /* line 125, ../scss/button.scss */
        .button-primary:hover {
            background-color: #00a1cb;
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #00c9fe), color-stop(100%, #008db2));
            background: -webkit-linear-gradient(top, #00c9fe, #008db2);
            background: -moz-linear-gradient(top, #00c9fe, #008db2);
            background: -o-linear-gradient(top, #00c9fe, #008db2);
            background: linear-gradient(top, #00c9fe, #008db2);
        }
        /* line 129, ../scss/button.scss */
        .button-primary:active {
            background: #1495b7;
            color: #005065;
        }

        /* line 118, ../scss/button.scss */
        .button-action {
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #8fcf00), color-stop(100%, #6b9c00));
            background: -webkit-linear-gradient(top, #8fcf00, #6b9c00);
            background: -moz-linear-gradient(top, #8fcf00, #6b9c00);
            background: -o-linear-gradient(top, #8fcf00, #6b9c00);
            background: linear-gradient(top, #8fcf00, #6b9c00);
            background-color: #7db500;
            border-color: #5a8200;
            color: white;
            text-shadow: 0 -1px 1px rgba(19, 28, 0, 0.35);
        }
        /* line 125, ../scss/button.scss */
        .button-action:hover {
            background-color: #7db500;
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #a0e800), color-stop(100%, #6b9c00));
            background: -webkit-linear-gradient(top, #a0e800, #6b9c00);
            background: -moz-linear-gradient(top, #a0e800, #6b9c00);
            background: -o-linear-gradient(top, #a0e800, #6b9c00);
            background: linear-gradient(top, #a0e800, #6b9c00);
        }
        /* line 129, ../scss/button.scss */
        .button-action:active {
            background: #76a312;
            color: #374f00;
        }

        /* line 118, ../scss/button.scss */
        .button-highlight {
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #fa9915), color-stop(100%, #d87e04));
            background: -webkit-linear-gradient(top, #fa9915, #d87e04);
            background: -moz-linear-gradient(top, #fa9915, #d87e04);
            background: -o-linear-gradient(top, #fa9915, #d87e04);
            background: linear-gradient(top, #fa9915, #d87e04);
            background-color: #f18d05;
            border-color: #bf7004;
            color: white;
            text-shadow: 0 -1px 1px rgba(91, 53, 2, 0.35);
        }
        /* line 125, ../scss/button.scss */
        .button-highlight:hover {
            background-color: #f18d05;
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #fba42e), color-stop(100%, #d87e04));
            background: -webkit-linear-gradient(top, #fba42e, #d87e04);
            background: -moz-linear-gradient(top, #fba42e, #d87e04);
            background: -o-linear-gradient(top, #fba42e, #d87e04);
            background: linear-gradient(top, #fba42e, #d87e04);
        }
        /* line 129, ../scss/button.scss */
        .button-highlight:active {
            background: #d8891e;
            color: #8d5303;
        }

        /* line 118, ../scss/button.scss */
        .button-caution {
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #e8543f), color-stop(100%, #d9331a));
            background: -webkit-linear-gradient(top, #e8543f, #d9331a);
            background: -moz-linear-gradient(top, #e8543f, #d9331a);
            background: -o-linear-gradient(top, #e8543f, #d9331a);
            background: linear-gradient(top, #e8543f, #d9331a);
            background-color: #e54028;
            border-color: #c22d18;
            color: white;
            text-shadow: 0 -1px 1px rgba(103, 24, 13, 0.35);
        }
        /* line 125, ../scss/button.scss */
        .button-caution:hover {
            background-color: #e54028;
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #eb6855), color-stop(100%, #d9331a));
            background: -webkit-linear-gradient(top, #eb6855, #d9331a);
            background: -moz-linear-gradient(top, #eb6855, #d9331a);
            background: -o-linear-gradient(top, #eb6855, #d9331a);
            background: linear-gradient(top, #eb6855, #d9331a);
        }
        /* line 129, ../scss/button.scss */
        .button-caution:active {
            background: #cd5240;
            color: #952312;
        }

        /* line 118, ../scss/button.scss */
        .button-royal {
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #99389f), color-stop(100%, #752a79));
            background: -webkit-linear-gradient(top, #99389f, #752a79);
            background: -moz-linear-gradient(top, #99389f, #752a79);
            background: -o-linear-gradient(top, #99389f, #752a79);
            background: linear-gradient(top, #99389f, #752a79);
            background-color: #87318c;
            border-color: #632466;
            color: white;
            text-shadow: 0 -1px 1px rgba(26, 9, 27, 0.35);
        }
        /* line 125, ../scss/button.scss */
        .button-royal:hover {
            background-color: #87318c;
            background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #ab3eb2), color-stop(100%, #752a79));
            background: -webkit-linear-gradient(top, #ab3eb2, #752a79);
            background: -moz-linear-gradient(top, #ab3eb2, #752a79);
            background: -o-linear-gradient(top, #ab3eb2, #752a79);
            background: linear-gradient(top, #ab3eb2, #752a79);
        }
        /* line 129, ../scss/button.scss */
        .button-royal:active {
            background: #764479;
            color: #3e1740;
        }

        /* line 143, ../scss/button.scss */
        .button-flat {
            -webkit-transition-property: background, color;
            -moz-transition-property: background, color;
            -o-transition-property: background, color;
            transition-property: background, color;
            -webkit-transition-duration: 0.3s;
            -moz-transition-duration: 0.3s;
            -o-transition-duration: 0.3s;
            transition-duration: 0.3s;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            background: #e9e9e9;
            border: none;
            text-shadow: none;
        }
        /* line 152, ../scss/button.scss */
        .button-flat:hover {
            background: #dcdada;
        }
        /* line 155, ../scss/button.scss */
        .button-flat:active {
            background: #eeeeee;
            color: #bbbbbb;
        }
        /* line 159, ../scss/button.scss */
        .button-flat.disabled {
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
        }

        /* line 171, ../scss/button.scss */
        .button-flat-primary {
            -webkit-transition-property: background, color;
            -moz-transition-property: background, color;
            -o-transition-property: background, color;
            transition-property: background, color;
            -webkit-transition-duration: 0.3s;
            -moz-transition-duration: 0.3s;
            -o-transition-duration: 0.3s;
            transition-duration: 0.3s;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            background: #00a1cb;
            color: white;
            text-shadow: none;
            border: none;
        }
        /* line 180, ../scss/button.scss */
        .button-flat-primary:hover {
            background: #00b5e5;
        }
        /* line 183, ../scss/button.scss */
        .button-flat-primary:active {
            background: #1495b7;
            color: #00647f;
        }
        /* line 187, ../scss/button.scss */
        .button-flat-primary.disabled {
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
        }

        /* line 171, ../scss/button.scss */
        .button-flat-action {
            -webkit-transition-property: background, color;
            -moz-transition-property: background, color;
            -o-transition-property: background, color;
            transition-property: background, color;
            -webkit-transition-duration: 0.3s;
            -moz-transition-duration: 0.3s;
            -o-transition-duration: 0.3s;
            transition-duration: 0.3s;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            background: #7db500;
            color: white;
            text-shadow: none;
            border: none;
        }
        /* line 180, ../scss/button.scss */
        .button-flat-action:hover {
            background: #8fcf00;
        }
        /* line 183, ../scss/button.scss */
        .button-flat-action:active {
            background: #76a312;
            color: #486900;
        }
        /* line 187, ../scss/button.scss */
        .button-flat-action.disabled {
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
        }

        /* line 171, ../scss/button.scss */
        .button-flat-highlight {
            -webkit-transition-property: background, color;
            -moz-transition-property: background, color;
            -o-transition-property: background, color;
            transition-property: background, color;
            -webkit-transition-duration: 0.3s;
            -moz-transition-duration: 0.3s;
            -o-transition-duration: 0.3s;
            transition-duration: 0.3s;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            background: #f18d05;
            color: white;
            text-shadow: none;
            border: none;
        }
        /* line 180, ../scss/button.scss */
        .button-flat-highlight:hover {
            background: #fa9915;
        }
        /* line 183, ../scss/button.scss */
        .button-flat-highlight:active {
            background: #d8891e;
            color: #a66103;
        }
        /* line 187, ../scss/button.scss */
        .button-flat-highlight.disabled {
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
        }

        /* line 171, ../scss/button.scss */
        .button-flat-caution {
            -webkit-transition-property: background, color;
            -moz-transition-property: background, color;
            -o-transition-property: background, color;
            transition-property: background, color;
            -webkit-transition-duration: 0.3s;
            -moz-transition-duration: 0.3s;
            -o-transition-duration: 0.3s;
            transition-duration: 0.3s;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            background: #e54028;
            color: white;
            text-shadow: none;
            border: none;
        }
        /* line 180, ../scss/button.scss */
        .button-flat-caution:hover {
            background: #e8543f;
        }
        /* line 183, ../scss/button.scss */
        .button-flat-caution:active {
            background: #cd5240;
            color: #ac2815;
        }
        /* line 187, ../scss/button.scss */
        .button-flat-caution.disabled {
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
        }

        /* line 171, ../scss/button.scss */
        .button-flat-royal {
            -webkit-transition-property: background, color;
            -moz-transition-property: background, color;
            -o-transition-property: background, color;
            transition-property: background, color;
            -webkit-transition-duration: 0.3s;
            -moz-transition-duration: 0.3s;
            -o-transition-duration: 0.3s;
            transition-duration: 0.3s;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            background: #87318c;
            color: white;
            text-shadow: none;
            border: none;
        }
        /* line 180, ../scss/button.scss */
        .button-flat-royal:hover {
            background: #99389f;
        }
        /* line 183, ../scss/button.scss */
        .button-flat-royal:active {
            background: #764479;
            color: #501d53;
        }
        /* line 187, ../scss/button.scss */
        .button-flat-royal.disabled {
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
        }

        /* line 202, ../scss/button.scss */
        .button-large {
            font-size: 19px;
            height: 38.4px;
            line-height: 38.4px;
            padding: 0px 30.72px;
        }

        /* line 227, ../scss/button.scss */
        input.button-large, button.button-large {
            height: 40.4px;
        }

        /* line 202, ../scss/button.scss */
        .button-small {
            font-size: 12px;
            height: 25.6px;
            line-height: 25.6px;
            padding: 0px 20.48px;
        }

        /* line 227, ../scss/button.scss */
        input.button-small, button.button-small {
            height: 27.6px;
        }

        /* line 202, ../scss/button.scss */
        .button-tiny {
            font-size: 11px;
            height: 22.4px;
            line-height: 22.4px;
            padding: 0px 17.92px;
        }

        /* line 227, ../scss/button.scss */
        input.button-tiny, button.button-tiny {
            height: 24.4px;
        }

        /* line 247, ../scss/button.scss */
        .button.glow {
            -webkit-animation-duration: 3s;
            -moz-animation-duration: 3s;
            -ms-animation-duration: 3s;
            -o-animation-duration: 3s;
            animation-duration: 3s;
            -webkit-animation-iteration-count: infinite;
            -khtml-animation-iteration-count: infinite;
            -moz-animation-iteration-count: infinite;
            -ms-animation-iteration-count: infinite;
            -o-animation-iteration-count: infinite;
            animation-iteration-count: infinite;
            -webkit-animation-name: glowing;
            -khtml-animation-name: glowing;
            -moz-animation-name: glowing;
            -ms-animation-name: glowing;
            -o-animation-name: glowing;
            animation-name: glowing;
        }
        /* line 250, ../scss/button.scss */
        .button.glow:active {
            -webkit-animation-name: none;
            -moz-animation-name: none;
            -ms-animation-name: none;
            -o-animation-name: none;
            animation-name: none;
            -webkit-box-shadow: inset 0px 1px 3px rgba(0, 0, 0, 0.3), 0px 1px 0px white;
            -moz-box-shadow: inset 0px 1px 3px rgba(0, 0, 0, 0.3), 0px 1px 0px white;
            box-shadow: inset 0px 1px 3px rgba(0, 0, 0, 0.3), 0px 1px 0px white;
        }
        /*button*/
        /*form*/
        /** 常用表单的样式 */
        .form-common-label {
            display: inline-block;
            text-align: left;
        }

        .form-common-text {
            display: inline-block;
            height: 22px;
            padding: 5px;
            width: 222px;
            border: #a9a9a9 solid 1px;
            position: relative;
            background: #ffffff;
        }

        .form-common-text input {
            border: 0;
            outline: none;
            width: 100%;
            margin: 2px 0;
            background-color: #ffffff;
        }

        .form-common-text-small {
            width: 150px;
        }
        .form-common-text-230 {
            width: 230px;
        }
        .form-common-text-big {
            width: 350px;
        }

        .form-common-text-validation {
            position: absolute;
            top: 21px;
            left: 0;
            color: #ff0000;
        }

        .form-common-btn-extend {
            border: #b3b3b3 solid 1px;
            border-radius: 3px;
            font-size: 18px;
            line-height: 20px;
            margin: 0 0 0 7px;
            color: #b3b3b3;
            height: 22px;
            width: 22px;
            text-align: center;
            overflow: hidden;
            display: inline-table;
            position: relative;
            cursor: pointer;
        }

        .form-common-btn-extend:hover {
            background-color: #3399ff;
            color: #fff;
            border: #589fe7 solid 1px;
        }

        .form-common-btn-extend:active {
            -webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.3), 0 1px 0 white;
            -moz-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.3), 0 1px 0 white;
            box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.3), 0 1px 0 white;
            text-shadow: 0 1px 0 rgba(255, 255, 255, 0.4);
            background: #1495b7;
            color: #547f7f;
            border-color: #107893;
            border-width: 0;
            padding: 1px;
        }
        /*form*/
        .con-login {
            width: 300px;
            margin: 50px auto;
            text-align: center;
        }
        .con-login .form-common-line {
            margin: 20px 0 0 0;
        }
        .con-login .form-common-label {
            width: 65px;
        }
        .con-login .btn-submit {
            margin: 20px 0 0 67px;
        }
    </style>
</head>
<body>
<div class="con-login">
    <form method="POST" action='j_security_check'>
        <div class="form-common-line ">
            <span class="form-common-label">用户名：</span>
 
            <div class="form-common-text form-common-text-small">
                <input type="text" name="j_username" class="" placeholder="" value=""/>
            </div>
        </div>
        <div class="form-common-line">
            <span class="form-common-label">密码：</span>

            <div class="form-common-text form-common-text-small">
                <input type="password" name="j_password" class="" placeholder="" value=""/>
            </div>
        </div>
        <input class="btn-submit button button-flat-primary m-20 j-button-submit" type="submit" value="登录">
    </form>
</div>
</body>
</html>