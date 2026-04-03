    package es.codeurjc.board.modelAttributes;

    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpSession;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import es.codeurjc.board.service.UserService;
    import java.security.Principal;
    import org.springframework.beans.factory.annotation.Autowired;

    @ControllerAdvice
    public class SessionAttributes {
        @Autowired
        private UserService userService;

        @ModelAttribute
        public void addAttributes(Model model, HttpServletRequest request, HttpSession session) {
            model.addAttribute("adminPanel", true );
            Principal principal = request.getUserPrincipal();
            System.out.println("URI: " + request.getRequestURI());
            if(principal != null) {
                model.addAttribute("loginOptions", true);
                //model.addAttribute("userName", principal.getName());

                model.addAttribute("adminOnly", request.isUserInRole("ADMIN"));
                if(userService.isUserUser(request)){
                    model.addAttribute("userOnly", request.isUserInRole("USER"));
                    model.addAttribute("userId", userService.getUserID(request));
                }   
            } else {
                model.addAttribute("loginOptions", false);
                model.addAttribute("userOnly", false);
                model.addAttribute("adminOnly", false);
                }
            
            //this attribute is for the quizzPlants
            String winner = (String) session.getAttribute("winnerPlant");
            if(winner != null){
                model.addAttribute("a", "a".equals(winner));
                model.addAttribute("b", "b".equals(winner));
                model.addAttribute("c", "c".equals(winner));
                model.addAttribute("nothing", false);

            } else{
                model.addAttribute("nothing", true);

            }

        }


        public static void hideAdminPanel(Model model){
            model.addAttribute("adminPanel", false );
        }

    }
