	function hideOptionsForAdmin(cartbtn,headerbtnToCatalogPlants){
		if(localStorage.getItem('isAdmin') === 'true'){
			if(cartbtn){
				cartbtn.style.display = 'none';
			}
			if (headerbtnToCatalogPlants) {
				headerbtnToCatalogPlants.style.display = 'none';
			}
			const botonForSeeingPlants= document.getElementById('Plantscatalog')
			if (botonForSeeingPlants) {
				botonForSeeingPlants.style.display = 'none';
			}
			document.querySelectorAll('.addToCartbtn').forEach(btn => { //esto es para eliminar flechas si solo hay una imagen
				btn.style.display = 'none';
			});
		} else{ //si no eres el admin, entonces no tienes panel de admin
			const adminPanel = document.getElementById('admin-btn');
			adminPanel.style.display = 'none';
		}
	}
	function hideOptionsMainPage(){
		if (window.location.pathname.includes('index.html')) {
			const gobackBtn = document.getElementById('goback-btn');
			if (gobackBtn) {
				gobackBtn.style.display = 'none';
			}
			const loginbtn = document.getElementById('login-btn');
			if(localStorage.getItem('isLoggedIn') === 'true'){
				if(loginbtn){
					loginbtn.style.display = 'none';
				}
			}
			const productos= document.getElementById('productos');
			if (productos) {
				productos.style.display = 'none';
			}
			const plantitas= document.getElementById('plantitas');
			if (plantitas) {
				plantitas.style.display = 'none';
			}
		}
	}
	function hideOptions() {
		const cartbtn = document.getElementById('cart-options');
		const headerbtnToCatalogPlants= document.getElementById('plantitas');

		hideOptionsForAdmin(cartbtn,headerbtnToCatalogPlants);
		hideOptionsMainPage();

		if (window.location.pathname.includes('catalogoProductos.html')) {
			const productos= document.getElementById('productos');
			if (productos) {
				productos.style.display = 'none';
			}
		}


		if (window.location.pathname.includes('sign-in.html')) {
			const changeuser = document.getElementById('changeuser-btn');
			if(changeuser){
				changeuser.style.display = 'none';
			}
		}
		

		if (window.location.pathname.includes('carroCompra.html')) {
			if(cartbtn){
				cartbtn.style.display = 'none';
			}
		}

		if (window.location.pathname.includes('catalogoPlantas.html')) {
			if (headerbtnToCatalogPlants) {
				headerbtnToCatalogPlants.style.display = 'none';
			}
		}

	}
	
	$(function(){   
		$("#footer-global").load("footer.html");
	});
    $(function(){   
		$("#header-global").load("header.html");
		hideOptions();
	});

