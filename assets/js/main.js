	function ocultarOpciones() {

		if (window.location.pathname.includes('catalogoProductos.html')) {
			const productos= document.getElementById('productos');
			if (productos) {
				productos.style.display = 'none';
			}
		}

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

		if (window.location.pathname.includes('sign-in.html')) {
			const changeuser = document.getElementById('changeuser-btn');
			if(changeuser){
				changeuser.style.display = 'none';
			}
		}
		

		if (window.location.pathname.includes('carroCompra.html')) {
			const cartbtn = document.getElementById('cart-options');
			if(cartbtn){
				cartbtn.style.display = 'none';
			}
		}

		if (window.location.pathname.includes('catalogoPlantas.html')) {
			const plantitas= document.getElementById('plantitas');
			if (plantitas) {
				plantitas.style.display = 'none';
			}
		}

	}
	
	$(function(){   
		$("#footer-global").load("footer.html");
		ocultarOpciones();
	});
    $(function(){   
		$("#header-global").load("header.html");
		ocultarOpciones();
	});

