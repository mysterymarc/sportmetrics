<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="../resources/styles/common.css" rel="stylesheet" type="text/css">
    <link href="../resources/styles/results.css" rel="stylesheet" type="text/css">
    <title>Sportmetrics</title>
    <script type="text/javascript">
        function getProperColor(indicator) {
            var color;
            if (indicator === "loss") {
                color = "#ef9c92";
            } else if (indicator === "win") {
                color = "#59ea77";
            } else if (indicator === "draw") {
                color = "#c0c0c0";
            } else color = "#ffffff";
            return color;
        }

        function setCellsBackgroundColor(cellClassName) {

            var cells = document.getElementsByClassName(cellClassName);

            <c:forEach items="${model.avgAnalysis.analysesGroup}" var="group" varStatus="grouploop">
            <c:forEach items="${group.analyses}" var="row" varStatus="rowloop">
            <c:forEach items="${row.segmentResults}" var="single" varStatus="celloop">
            cells[${grouploop.index*fn:length(row.segmentResults)}+${rowloop.index*fn:length(row.segmentResults)}+${celloop.index}]
                .setAttribute("bgcolor", getProperColor('${single.valueAttribute}'));
            </c:forEach>
            </c:forEach>
            </c:forEach>
        }
    </script>

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <br>
            <div class="row justify-content-center">
                <div class="card">
                    <h1 class="text-center m-2">${model.competition.name}</h1>
                </div>
            </div>
            <br>
            <div class="col-2">
                <table class="table table-bordered table-hover table-condensed table-sm">
                    <tr class="no-hover">
                        <th class="hover" onclick="location.href='results?competition_id=' + ${model.competition.id};">
                            << BACK TO EVENT RESULTS
                        </th>
                    </tr>
                </table>
            </div>

            <div class="col">
                <table class="table table-bordered table-condensed table-sm">
                    <c:forEach items="${model.results.rowsGroupsView}" var="group" varStatus="grouploop">
                        <tr>
                            <th colspan="100%">Results for Group ${grouploop.count}</th>
                        </tr>
                        <tr>
                            <th class="centered">position</th>
                            <th class="centered">name</th>
                            <th class="centered">city</th>
                            <th class="centered">total</th>
                            <th class="centered">delay</th>
                            <th class="centered">result</th>
                            <c:forEach items="${model.segments.segments}" var="segment">
                                <th class="centered" colspan="2">${segment.name}</th>
                            </c:forEach>
                        </tr>
                        <c:forEach items="${group.rowsView}" var="row">
                            <tr>
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
                        </c:forEach>
                    </c:forEach>

                    <c:forEach items="${model.avgAnalysis.analysesGroup}" var="group" varStatus="rowloop">
                        <tr>
                            <th class="centered" colspan="100%">Statistics for Group ${rowloop.count}</th>
                        </tr>
                        <c:forEach items="${group.analyses}" var="row">
                            <tr>
                                <td class="centered" colspan=6>${row.title}</td>
                                <c:forEach items="${row.segmentResults}" var="single">
                                    <td class="analyse-result" colspan=2 align="center">${single.value}</td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script>
    setCellsBackgroundColor('analyse-result');
</script>
</html>