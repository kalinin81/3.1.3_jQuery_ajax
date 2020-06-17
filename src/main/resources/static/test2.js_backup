
$(document).ready(function () {

    $.ajax("/rest", {
        dataType: "json",
        success: function (msg) {
            const div = $("#users");
            msg.forEach(function (el) {
                $("<div></div>").text(el.id).attr("id",el.id).appendTo(div);
            })
        }
    })

    const buttonDelete = $("#delete");

    buttonDelete.click(
        function () {
            $.ajax("/rest", {
                method: "DELETE",
                data: {id: $(this).attr("value")},
                dataType: "text",
                success: function (msg) {
                    $("#users").find("#"+msg).remove();

                }
            })
        }
    )

    const buttonEdit = $("#edit");
    buttonEdit.click(
        function () {
            $.ajax("/rest", {
                method: "put",
                data:
                    {
                        id: $(this).attr("value"),
                         login: $(this).parent().find("#login").attr("value")
                    },
                dataType: "text",
                success: function (msg) {
                     $("#users").find("#"+msg).text(";;;");

                }
            })
        }
    )
})