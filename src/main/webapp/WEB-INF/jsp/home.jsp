<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Currency calculator</title>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#result').hide();
            $('#submit').on('click', function() {
                getExchangeRate();
            });
        });

        function getExchangeRate() {
            var remittance = $('#remittance').val();
            if(!remittance) {
                alert('송금액이 바르지 않습니다');
                return;
            }

            if(remittance < 0 || remittance > 10000) {
                alert('송금액이 바르지 않습니다');
                return;
            }

            if(isNaN(remittance)) {
                alert('송금액이 바르지 않습니다');
                return;
            }

            var param = {
                country: $('#country option:selected').val()
            };

            $.get('/getExchangeRate', param, function(rJson) {
                if(rJson) {
                    var country = param.country;
                    var $exchange_rate = $('#exchange_rate');
                    $exchange_rate.val(rJson);

                    var suffix = " " + country.substring(3, country.length) + "/USD";
                    $('#suffix').text(suffix);

                    var remittance = $('#remittance').val();
                    var exchange_rate = $exchange_rate.val();
                    $exchange_rate.css('width',  $exchange_rate.val().length * 7);

                    var amount_received = Number(remittance) * Number(exchange_rate);
                    amount_received = numberWithCommas(amount_received.toFixed(2));
                    var $amount_received = $('#amount_received');
                    $amount_received.val(amount_received);
                    $amount_received.css('width', $amount_received.val().length * 7);

                    $('#result_suffix').text(country.substring(3, country.length));
                    $('#result').show();
                } else {
                    alert('환율정보를 가져오지 못했습니다.');
                }
            });
        }

        function numberWithCommas(x) {
            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
    </script>
</head>
<body>
    <div>
        <h2>환율 계산</h2>
    </div>
    <div>
        <span>송금국가: 미국(USD)</span><br/>
        <label for="country">수취국가:</label>
        <select name="country" id="country">
            <option value="USDKRW" selected="selected">한국(KRW)</option>
            <option value="USDJPY">일본(JPN)</option>
            <option value="USDPHP">필리핀(PHP)</option>
        </select>
        <br/>
        <label for="exchange_rate">환율: </label><input type="text" id="exchange_rate" name="exchange_rate" readonly="readonly" value="" style="border: 0;" /><span id="suffix"></span>
        <br/>
        <label for="remittance">송금액: </label><input type="text" id="remittance" name="remittance" /> USD
        <br/>
        <button id="submit">Submit</button>
        <br/><br/>
        <div id="result">수취금액은 <input type="text" id="amount_received" name="amount_received" value="" style="border: 0;" /> <span id="result_suffix"></span>입니다.</div>
    </div>
</body>
</html>