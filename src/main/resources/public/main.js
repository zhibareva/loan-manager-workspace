$("#take-loan-form").submit(function(event) {
    event.preventDefault();
    submit_loan();
});

$("#search-client").click(function(event) {
    event.preventDefault();
    search_clients_window();
});

$("#search-loan").click(function(event) {
    event.preventDefault();
    search_loans_window();
});

$("#contracts").click(function(event) {
    event.preventDefault();
    search_contracts_window();
});

$("#bth-search-client").click(function(event) {
    event.preventDefault();
    search_clients_filter();
});

$("#bth-search-loan").click(function(event) {
    event.preventDefault();
    search_loans_filter();
});

$("#take-loan").click(function(event) {
    event.preventDefault();
    take_loan();
});

$("#btn-continue").click(function(event) {
    event.preventDefault();
    $("#sign-contract-form").show();
    $("#btn-continue").hide();
});

$("#btn-sign-contract").click(function(event) {
     event.preventDefault();
     sign_contract();
 });

$("#bth-contract-loan").click(function(event) {
     event.preventDefault();
     search_contracts_filter();
 });

function take_loan() {
    $("#bth-search-client").hide();
    $("#btn-continue").hide();
    $("#feedback").hide();
    $("#search-client-form").hide();
    $("#search-clients-table").hide();
    $("#bth-search-client").hide();
    $("#found-clients tr").hide();

    $("#bth-search-loan").hide();
    $("#search-loan-form").hide();
    $("#search-loan-table").hide();
    $("#bth-search-loan").hide();
    $("#found-loans tr").hide();

    $("#bth-search-contract").hide();
    $("#search-contract-form").hide();
    $("#search-contract-table").hide();
    $("#bth-search-contract").hide();
    $("#found-contracts tr").hide();
    $("#sign-contract-form").hide();

    $('h1').text("Loan Application");
    $("#take-loan-form").show();
    $("#take-loan").addClass("active");
    $("#search-loan").removeClass("active");
    $("#search-client").removeClass("active");
    $("#contracts").removeClass("active");
}

function search_clients_window() {
    $("#btn-continue").hide();
    $("#take-loan-form").hide();
    $("#feedback").hide();

    $("#bth-search-loan").hide();
    $("#search-loan-form").hide();
    $("#search-loan-table").hide();
    $("#bth-search-loan").hide();
    $("#found-loans tr").hide();

    $("#bth-search-contract").hide();
    $("#search-contract-form").hide();
    $("#search-contract-table").hide();
    $("#bth-search-contract").hide();
    $("#found-contracts tr").hide();
    $("#sign-contract-form").hide();

    $("#bth-search-client").show();
    $("#search-client-form").show();

    $('h1').text("Search Clients");
    $("#take-loan").removeClass("active");
    $("#search-client").addClass("active");
    $("#search-loan").removeClass("active");
    $("#contracts").removeClass("active");

}

function search_loans_window() {

    $("#take-loan-form").hide();
    $("#btn-continue").hide();
    $("#feedback").hide();

    $("#bth-search-client").hide();
    $("#search-client-form").hide();
    $("#search-clients-table").hide();
    $("#bth-search-client").hide();
    $("#found-clients tr").hide();

    $("#bth-search-contract").hide();
    $("#search-contract-form").hide();
    $("#search-contract-table").hide();
    $("#bth-search-contract").hide();
    $("#found-contracts tr").hide();
    $("#sign-contract-form").hide();

    $("#search-loan-form").show();
    $("#bth-search-loan").show();

    $('h1').text("Search Loan Application");
    $("#take-loan").removeClass("active");
    $("#search-client").removeClass("active");
    $("#search-loan").addClass("active");
    $("#contracts").removeClass("active");


}

function search_contracts_window() {
    $("#bth-search-client").hide();
    $("#btn-continue").hide();
    $("#feedback").hide();
    $("#search-client-form").hide();
    $("#search-clients-table").hide();
    $("#bth-search-client").hide();
    $("#found-clients tr").hide();

    $("#btn-continue").hide();
    $("#take-loan-form").hide();
    $("#feedback").hide();

    $("#bth-search-loan").hide();
    $("#search-loan-form").hide();
    $("#search-loan-table").hide();
    $("#bth-search-loan").hide();
    $("#found-loans tr").hide();

    $("#search-contract-form").show();
    $('h1').text("Search Contracts");
    $("#take-loan").removeClass("active");
    $("#search-client").removeClass("active");
    $("#search-loan").removeClass("active");
    $("#contracts").addClass("active");
}

function submit_loan() {

    var search = {}
    search["firstName"] = $("#firstName").val();
    search["middleName"] = $("#middleName").val();
    search["lastName"] = $("#lastName").val();
    search["passportId"] = $("#passportId").val();
    search["placeOfResidence"] = $("#placeOfResidence").val();
    search["phoneNumber"] = $("#phoneNumber").val();
    search["from"] = $("#from").val();
    search["to"] = $("#to").val();
    search["position"] = $("#position").val();
    search["companyName"] = $("#companyName").val();
    search["loanAmount"] = $("#loanAmount").val();

    $("#btn-search").prop("disabled", true);
    $("#spinner-border").removeAttr('hidden');
    $("#feedback").show();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/v1/loanApplications",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function(data) {

            var json = "<h4>Loan Decision</h4><br>" +
                "<ul class='list-group w-25'>" +
                "<li class='list-group-item'>ID: <div id='loanId'>" + data.id + "</div></li>" +
                "<li class='list-group-item'>Client: " + data.firstName + " " + data.middleName + " " + data.lastName + "</li>" +
                "<li class='list-group-item'>Loan term: " + data.periodInDays + " days</li>";

            if (data.approved) {
                json += "<li class='list-group-item'>Approved Loan Amount: " + data.approvedLoanAmount + "</li>"
                json += "<li class='list-group-item'><span class='badge bg-success'> Approved </span></li>";
            } else {
                json += "<li class='list-group-item'><span class='badge bg-danger'> Denied </span></li>";
            }
            json += "</ul>";
            $("#spinner-border").hide();

            $('#feedback').html(json);
            if (data.approved) {
                $("#btn-continue").show();
            }

            console.log("SUCCESS : ", data);

            $("#btn-search").prop("disabled", false);
            $("#take-loan-form").hide();
        },
        error: function(e) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;" +
                e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}

function sign_contract(){
    var json = {}
    json["dateOfSignature"] = new Date().toISOString().slice(0, 10);
    if ($("#isSigned").prop("checked")) {
                json["isSigned"] = true;
    } else {
      json["isSigned"] = false;
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/v1/loanApplications/"+ $("#loanId").text() +"/contracts",
        data: JSON.stringify(json),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function(data) {
        var alert = {}
        if(json["isSigned"]){
            alert += "<div class='alert alert-success' role='alert'> Congratulation! Loan is successfully issued! </div>"
        } else {
            alert += "<div class='alert alert-warning' role='alert'> Loan is not signed :C </div>"
        }
          $('#sign-contract-form').hide();
          $('#feedback').show();
          $('#feedback').html(alert);

        },
        error: function(e) {
            $('#feedback').show();
            var json = "<h4>Ajax Response</h4>&lt;pre&gt;" +
                e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}

function search_clients_filter() {

    $("#bth-search-client").prop("disabled", true);
    $("#found-clients tr").remove();
    $("#search-clients-table").show();
    $("#found-clients tr").show();

    $.ajax({
        type: "GET",
        url: "/v1/clients",
        data: {
            lastName: $("#lastNameSearch").val(),
            passportId: $("#passportIdSearch").val(),
            phoneNumber: $("#phoneNumberSearch").val()
        },
        cache: false,
        timeout: 600000,
        success: function(response) {
            $.each(response, function(key, value) {
                $('#found-clients').append("<tr>\
        										<td>" + value.firstName + "</td>\
        										<td>" + value.middleName + "</td>\
        										<td>" + value.lastName + "</td>\
        										<td>" + value.phoneNumber + "</td>\
        										<td>" + value.passportId + "</td>\
        										<td>" + value.placeOfResidence + "</td>\
        										<td>" + value.companyName + "</td>\
        										<td>" + value.position + "</td>\
        										<td>" + value.from + "</td>\
        										<td>" + value.to + "</td>\
        										</tr>");
            })

            $("#bth-search-client").prop("disabled", false);
            $("#take-loan-form").hide();
        },
        error: function(e) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;" +
                e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}

function search_loans_filter() {
    var search = {}
    if ($("#checkApproved").prop("checked") && $("#checkDenied").prop("checked")) {

    } else if ($("#checkApproved").prop("checked")) {
        search["status"] = true;
    } else if ($("#checkDenied").prop("checked")) {
        search["status"] = false;
    }

    $("#found-loans tr").remove();
    $("#search-loan-table").show();
    $("#found-loans tr").show();
    $("#bth-search-loan").prop("disabled", true);
    $.ajax({
        type: "GET",
        url: "/v1/loanApplications",
        data: search,
        cache: false,
        timeout: 600000,
        success: function(response) {
            $.each(response, function(key, value) {
                $('#found-loans').append("<tr>\
        										<td>" + value.firstName + "</td>\
        										<td>" + value.middleName + "</td>\
        										<td>" + value.lastName + "</td>\
        										<td>" + value.periodInDays + "</td>\
                            <td>" + value.approvedLoanAmount + "</td>\
        										<td>" + value.approved + "</td>\
        										</tr>");
            })

            $("#bth-search-loan").prop("disabled", false);
            $("#take-loan-form").hide();
        },
        error: function(e) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;" +
                e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });
}

function search_contracts_filter() {
    var search = {}
    if ($("#checkSigned").prop("checked") && $("#checkNotSigned").prop("checked")) {

    } else if ($("#checkSigned").prop("checked")) {
        search["isSigned"] = true;
    } else if ($("#checkNotSigned").prop("checked")) {
        search["isSigned"] = false;
    }

    $("#found-contracts tr").remove();
    $("#search-contract-table").show();
    $("#found-contracts tr").show();
    $("#bth-search-contract").prop("disabled", true);
    $.ajax({
        type: "GET",
        url: "/v1/contracts",
        data: search,
        cache: false,
        timeout: 600000,
        success: function(response) {
            $.each(response, function(key, value) {
                $('#found-contracts').append("<tr>\
        										<td>" + value.firstName + "</td>\
        										<td>" + value.middleName + "</td>\
        										<td>" + value.lastName + "</td>\
        										<td>" + value.periodInDays + "</td>\
                            <td>" + value.approvedLoanAmount + "</td>\
        										<td>" + value.approved + "</td>\
        										<td>" + value.dateOfSignature + "</td>\
        										<td>" + value.signed + "</td>\
        										</tr>");
            })

            $("#bth-search-contract").prop("disabled", false);
            $("#take-loan-form").hide();
        },
        error: function(e) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;" +
                e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });
}