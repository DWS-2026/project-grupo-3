	function hideOptionsForAdmin(cartbtn,headerbtnToCatalogPlants){
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
			if(window.location.pathname.includes('admin-panel.html')){
				const adminPanel = document.getElementById('admin-btn');
				adminPanel.style.display = 'none';
			}
			document.querySelectorAll('.user-only').forEach(el => {
                el.style.display = 'none';
            });

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

		

		if(localStorage.getItem('isLoggedIn') === 'true') { //querySelectorAll te devuelve directamente una lista enlazada con los elementos de esa clase
			if(localStorage.getItem('isAdmin') === 'true'){
				hideOptionsForAdmin(cartbtn,headerbtnToCatalogPlants);
			}else{
				const adminPanel = document.getElementById('admin-btn');
				adminPanel.style.display = 'none';
			}

        }else{
			document.querySelectorAll('.login-options').forEach(el => { //selecciona todos los elem de la clase login-options
                el.style.display = 'none';
            });
		}
		
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

