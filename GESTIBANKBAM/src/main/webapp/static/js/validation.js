 function validateTransaction() {
            var src_ele = document.getElementById("cpt_origin");
            var dst_ele = document.getElementById("cpt_dest");
            var montant_ele = document.getElementById("leMontant");
            
            var src = src_ele.value;
            var dst = dst_ele.value;
            var montant = montant_ele.value;
            
            

            console.log("montant="+montant);
            console.log("Origin="+src);
            console.log("Déstination="+dst);
            
           var res= confirm("Effectuer un virement de " +
                              montant +
                              " Euro"+"\n"+ "du compte : " +
                              src +"\n"+
                              "vers le compte : " +
                              dst + "\n"+
                              "Voulez-vous confirmer?");

         // document.getElementById("valid").innerHTML = res;

            
        }