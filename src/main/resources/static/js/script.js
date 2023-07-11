const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');

allSideMenu.forEach(item=> {
 const li = item.parentElement;

 item.addEventListener('click', function () {
  allSideMenu.forEach(i=> {
   i.parentElement.classList.remove('active');
  })
  li.classList.add('active');
 })
});




// TOGGLE SIDEBAR
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('sidebar');
const profileLink = document.querySelector('#sidebar .profile');
const firstMenuItem = document.querySelector('#sidebar .side-menu.top');
menuBar.addEventListener('click', function () {
 sidebar.classList.toggle('hide');
 if (sidebar.classList.contains('hide')) {
    profileLink.remove();
  } else {
    sidebar.insertBefore(profileLink, firstMenuItem);
  }
})