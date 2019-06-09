<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"--%>
<%--          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">--%>
    <link href="../resources/external/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="../resources/styles/common.css" rel="stylesheet" type="text/css">
    <link href="../resources/styles/addEvent.css" rel="stylesheet" type="text/css">
    <title>Sportmetrics</title>
</head>
<body>
<br>
<div class="container">
    <div aria-live="polite" aria-atomic="true">
        <div class="toast toast-success" data-delay="5000">
            <div class="toast-header">
                <strong class="mr-auto">Success</strong>
                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="toast-body">
                Your data was successfully loaded
            </div>
        </div>
    </div>
    <div aria-live="polite" aria-atomic="true">
        <div class="toast toast-failure" data-delay="5000">
            <div class="toast-header">
                <strong class="mr-auto">Failure</strong>
                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="toast-body">
                Data not loaded. Check log for details.
            </div>
        </div>
    </div>
    <br>
    <br>
    <br>
    <div class="row justify-content-center">
        <div class="card">
            <h5 class="text-center m-2">Select JSON file with event's results</h5>
            <form action="uploadEventResults" method="post"
                  enctype="multipart/form-data">
                <div class="custom-file w-75 p-2 m-5">
                    <input name="file" type="file" class="custom-file-input" id="validatedCustomFile" required>
                    <label class="custom-file-label" for="validatedCustomFile">Choose file...</label>
                    <div class="invalid-feedback">Example invalid custom file feedback</div>
                    <input class="form-control graybutton" type="submit" value="Upload File"/>
                </div>
            </form>
            <hr class="divider">
            <a href="events" class="text-center m-2 text-white"><h5>&lt&lt BACK TO ALL REGISTERED EVENTS</h5></a>
        </div>
    </div>
</div>

<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"--%>
<%--        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"--%>
<%--        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"--%>
<%--        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"--%>
<%--        crossorigin="anonymous"></script>--%>
<script src="../resources/external/jquery/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="../resources/external/ajax/libs/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="../resources/external/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script>
    $('#validatedCustomFile').on('change', function () {
        var fileName = $(this).val().replace('C:\\fakepath\\', " ");
        $(this).next('.custom-file-label').html(fileName);
    })
</script>
<script>
    $(document).ready(function () {
        if ('${uploadResult}' === 'success') {
            $('.toast-success').toast('show');
        } else if ('${uploadResult}' === 'failure') {
            $('.toast-failure').toast('show');
        }
    });
</script>
</body>
</html>
