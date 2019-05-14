<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="../resources/styles/common.css" rel="stylesheet" type="text/css">
    <link href="../resources/styles/results.css" rel="stylesheet" type="text/css">
    <title>Sportmetrics</title>
    <script type="text/javascript">
        var analyse1Items = [];
        var analyse2Items = [];

        function selection(checkbox, totalId) {
            var list;

            if (checkbox.getAttribute('group') === 'analyse1') {
                list = analyse1Items;
            } else {
                list = analyse2Items;
            }

            if (checkbox.checked === true) {
                list.push(totalId);
            } else {
                for (var i = 0; i < list.length; i++) {
                    if (list[i] === totalId) {
                        list.splice(i, 1);
                    }
                }
            }
        }

        function goToAnalyse() {
            if (analyse1Items.length === 0 || analyse2Items.length === 0) {
                $('.toast-failure').toast('show');
            } else {
                location.href = 'analyse?competitionId=' + '${model.competition.id}' +
                    '\&firstGroup=' + analyse1Items.toString() + '\&secondGroup=' + analyse2Items.toString();

                analyse1Items = [];
                analyse2Items = [];
            }
        }
    </script>
</head>
<body>
<br>
<div class="container-fluid">
    <div aria-live="polite" aria-atomic="true">
        <div class="toast toast-failure" data-delay="5000">
            <div class="toast-header">
                <strong class="mr-auto">Failure</strong>
                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="toast-body">
                Select at last one record for each group.
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <div class="row justify-content-center">
                <div class="card">
                    <h1 class="text-center m-2">${model.competition.name}</h1>
                </div>
            </div>
            <br>
            <div class="col-3">
                <table class="table table-bordered table-hover table-condensed table-sm">
                    <tr class="no-hover">
                        <th class="hover" onclick="location.href='events'">
                            << BACK TO EVENT LIST
                        </th>
                        <th class="hover" onclick="goToAnalyse()">
                            ANALYSE >>
                        </th>
                    </tr>
                </table>
            </div>
            <div class="col-12">
                <%--            <table border="1">--%>
                <table class="table table-bordered table-condensed table-sm">
                    <tr>
                        <th class="centered">group 1</th>
                        <th class="centered">group 2</th>
                        <th class="centered">position</th>
                        <th class="centered">name</th>
                        <th class="centered">city</th>
                        <th class="centered">total</th>
                        <th class="centered">delay</th>
                        <th class="centered">result</th>
                        <c:forEach items="${model.segments}" var="segment">
                            <th class="centered" colspan="2">${segment.name}</th>
                        </c:forEach>
                    </tr>
                    <c:forEach items="${model.resultRows.rowsView}" var="row" varStatus="status">
                        <div class="row">
                            <tr>
                                <td class="centered" rowspan="2">
                                    <div class="custom-control custom-checkbox">
                                        <label class="place">**</label>
                                        <input type="checkbox" class="custom-control-input" id="col1row${status.index}"
                                               group="analyse1" onclick="selection(this,${row.totalResultId})"
                                               autocomplete="off">
                                        <label class="custom-control-label" for="col1row${status.index}"></label>
                                    </div>
                                </td>
                                <td class="centered" rowspan="2">
                                    <div class="custom-control custom-checkbox">
                                        <label class="place">**</label>
                                        <input type="checkbox" class="custom-control-input" id="col2row${status.index}"
                                               group="analyse2" onclick="selection(this,${row.totalResultId})"
                                               autocomplete="off">
                                        <label class="custom-control-label" for="col2row${status.index}"></label>
                                    </div>
                                </td>
                                <td class="centered" rowspan="2">${row.position}</td>
                                <td class="vertical-centered" rowspan="2">${row.competitorName}</td>
                                <td class="vertical-centered" rowspan="2">${row.competitorCity}</td>
                                <td class="times" rowspan="2">${row.totalTime}</td>
                                <td class="times" rowspan="2">${row.delayTime}</td>
                                <td class="centered">single</td>
                                <c:forEach items="${row.segmentResults}" var="partial">
                                    <td class="segment-time">${partial.time}</td>
                                    <td class="segment-position">${partial.position}</td>
                                </c:forEach>
                            </tr>
                            <tr>
                                <td class="centered">cumul.</td>
                                <c:forEach items="${row.cumulativeResults}" var="partial">
                                    <td class="cumul-time">${partial.time}</td>
                                    <td class="cumul-position">${partial.position}</td>
                                </c:forEach>
                            </tr>
                        </div>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>