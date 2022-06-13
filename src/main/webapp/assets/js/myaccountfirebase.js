
// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyDgwzyWTbHB7QrbJz-nW06YiNu_gjAIM1E",
    authDomain: "mioca-17b89.firebaseapp.com",
    projectId: "mioca-17b89",
    storageBucket: "mioca-17b89.appspot.com",
    messagingSenderId: "576021263099",
    appId: "1:576021263099:web:db6b6946cf55bced80b65e"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
var files;

document.getElementById("img").addEventListener("change", function(e) {
    document.getElementById("loading").classList.remove("hidden-load");
    files = e.target.files;
    //checks if files are selected
    if (files != undefined && files != null && files.length != 0) {
        //Loops through all the selected files
        for (let i = 0; i < files.length; i++) {
            //create a storage reference

            let temp = files[i].name.split(".");
            files[i].name = temp[0]+Date.now()+ temp[1];
            //var storage = firebase.storage().ref(files[i].name+);
            var storage = firebase.storage().ref(temp[0]+Date.now()+"."+ temp[1]);
            //upload file
            var upload = storage.put(files[i]);
            //var storageRef = firebase.storage().ref(imageName);
            var uploadTask = storage.put(files[i]);
            //update progress bar
            upload.on(
                "state_changed",
                function progress(snapshot) {

                },
                function error(e) {
                    alert(e);
                },
                function () {
                    uploadTask.snapshot.ref
                        .getDownloadURL()
                        .then(async function (downloadURL) {
                            document.getElementById("urlImage").value = downloadURL;
                            alert("Update Successfully");
                            document.getElementById("loading").classList.add("hidden-load");
                        });
                },
            );
        }
    } else {
        alert("Please Update Image");
    }
});
