let tableBody = $('tbody');

$(document).ready(() => {
  let tableBody = $('tbody');
  getData();
  $.datetimepicker.setLocale('en');
  $('.date-picker').datetimepicker({
    format: 'c',
  });

  $('#create-entry-trigger').on('click', evt => {
    console.log('click');
    evt.preventDefault();
    const checkIn = new Date($('#check-in').val()).toISOString();
    const checkOut = new Date($('#check-out').val()).toISOString();
    createEntry({ checkIn, checkOut });
  });
});

function getData() {
  $.ajax({
    url: "http://localhost:8081/entries",
    context: document.body,
    method: 'GET',
    dataType: 'json',
  }).done(response => {
    for (let i = 0; i < response.length; i += 1) {
      tableBody.append(
        `<tr>
          <td>${response[i].id}</td>
          <td>${response[i].checkIn}</td>
          <td>${response[i].checkOut}</td>
          <td>
            <button class="waves-effect waves-light btn" onclick="deleteEntry(${response[i].id});">Delete</button>
          </td>
        </tr>`
      );
    }
  });
}

function createEntry(dataToSend) {
  $.ajax({
    url: `http://localhost:8081/entries`,
    context:  document.body,
    method: 'POST',
    dataType: 'json',
    data: JSON.stringify(dataToSend),
    contentType: "application/json; charset=utf-8",
  }).done(() => {
    tableBody.empty();
    getData();
    M.toast({ html: 'Entry has been created' });
  });
}

function deleteEntry(id) {
  $.ajax({
    url: `http://localhost:8081/entries/${id}`,
    context: document.body,
    method: 'DELETE',
    dataType: 'json',
  }).done(() => {
    tableBody.empty();
    M.toast({ html: 'Entry has been deleted' });
    getData();
  });
}
