app.controller('mainCtrl', function($scope) {});
app.controller('contactCtrl', ['$scope', '$routeParams', '$location','$q','ClientService', 'ClientServiceNew',
    function($scope, $routeParams, $location,$q, ClientService,ClientServiceNew) {
    var self = this;
    self.users=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

app.controller('seeCtrl', ['$scope', '$location', 'CompteService','$routeParams',
                           function($scope, $location, CompteService, $routeParams) {
    console.log("Compte view");
    }]);

app.controller('see_clCtrl', [ '$scope', '$location', 'ClientIdService','ClientUpdateService','CompteService','$routeParams',
        function($scope, $location, ClientIdService,ClientUpdateService, $routeParams,CompteService) {
            console.log("hello toi");
            ClientIdService.searchClient($routeParams.id).then(function(d) {
                self.user = d;
                $scope.user = d;
                console.log($scope.user);
            }, function(errResponse) {
                console.error('Error while fetching Users');
            });
            
            $scope.send= function (client,id){
           	 console.log(client);
           	 console.log(id);
           	 ClientUpdateService.UpdateClient(client,id);
           };  
           $scope.commande= function(){
               console.log("test");  
               var cpt = $scope.cpt.numcpt;
               CompteService.commanderChequier(cpt)   
              }
        } ]);

app.controller('compteCtrl', [ '$scope', '$location', 'CompteService','$routeParams',
       function($scope, $location, CompteService, $routeParams) {
           console.log("hello id");
           CompteService.fetchCompte($routeParams.cpt).then(function(d) {
               self.cpt= d;
               console.log("cpt : " + cpt);
               $scope.cpt = d;
               console.log($scope.cpt);
           }, function(errResponse) {
               console.error('Error while fetching Compte');
           });
       } ]);

app.controller('see_conCtrl', [ '$scope', '$location', 'EmployeService','ConseillerUpdateService',
                                '$routeParams',
                                function($scope, $location, EmployeService,ConseillerUpdateService, $routeParams) {
                                    console.log("hello id");
                                    EmployeService.searchCons($routeParams.id).then(function(d) {
                                        self.cons = d;
                                        $scope.cons = d;
                                        console.log($scope.user);
                                    }, function(errResponse) {
                                        console.error('Error while fetching Users');
                                    });
                                    $scope.sen= function (conseiller,id){
                                      	 console.log(conseiller);
                                       	 console.log(id);
                                       	 ConseillerUpdateService.UpdateConseiller(conseiller,id);
                                       }   
                                } ]);
//transfer 
app.controller('transferCtrl', ['$scope', '$location','CompteService',
    function($scope, $location,CompteService) {
	console.log=($scope.cpt_origine);
	$scope.submit = function (){
		var cpt1=$scope.cpt_origine.numCpt;
		var cpt2=$scope.cpt_dest;
		var mont=$scope.mont;
		CompteService.virer(cpt1, cpt2, mont);
	};
    }]);

//notify   
app.controller('notifyCtrl', ['$scope', '$location', 
    function($scope, $location) {
	var d = null;
	var compte= null;
	d =sessionStorage.getItem("pers");
	pers = JSON.parse(d);
	var compte = pers.comptes;
	$scope.notif=compte;
    }]);
    
//request       
app.controller('requestCtrl', ['$scope', '$location', 'ClientService',
    function($scope, $location, ClientService) {
        $scope.open=function(){
        	var util =$scope.util;
        	var mont= $scope.mont;
        	ClientService.OpenNewCompte(util.id, mont);
        }
    }]);

//print         
app.controller('printCtrl', ['$scope', '$location', 
    function($scope, $location) {
	 	$scope.PrintElem=function printToCart(printSectionId){
         var innerContents = document.getElementById(printSectionId).innerHTML;
         var popupWindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
         popupWindow.document.open();
         popupWindow.document.write('<html><head><link rel="stylesheet" type="text/css" href="static/css/print.css" window.print(); /></head><body >' + innerContents + '</html>');
         popupWindow.document.close();
	 	};
    }]);

//update        
app.controller('updateCtrl', ['$scope', '$location', 
    function($scope, $location) {
        // todo
    }]);

//search_ag     
app.controller('search_agCtrl',  ['$scope', '$location','ClientService','$routeParams','ClientFilterService','$window',
  function($scope, $location, ClientService,$routeParams,ClientFilterService,$window){
    console.log("fetching All Clients");
    ClientFilterService.fetchFilteredClients($routeParams.id)
        .then(
        function(d) {
            self.users = d;
            $scope.users=d;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        }
    );
    
    $scope.sm = function (nom,prenom){
    	 console.log("Entered"); 
    ClientService.srcClient(nom,prenom)
    
    .then(
            function(d) {
                self.users = d;
                $scope.users=d;
                console.log($scope.users);
                
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
}
]);

//search_ad
app.controller('search_adCtrl', ['$scope', '$location','EmployeService','EmpSearchService',
     function($scope, $location, EmployeService,EmpSearchService)
     {
            console.log("hello2");
            EmployeService.fetchConseiller()
                .then(
                function(d) {
                    self.cons = d;
                    $scope.cons=d;
                    console.log($scope.cons);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            ); 
            
            $scope.srcCons = function (nom,prenom){
            	console.log(nom,prenom);
            	EmpSearchService.fetchCons(nom,prenom)
            	  .then(
                          function(d) {
                              self.cons = d;
                              $scope.cons=d;
                              console.log($scope.cons);
                          },
                          function(errResponse){
                              console.error('Error while fetching Users');
                          }
                      ); 
            }
}]);

                        
//consult_agCtrl
app.controller('consult_agCtrl', [ '$scope', '$location', 'ClientMdpService','ClientServiceAff','$routeParams',
        function($scope, $location, ClientMdpService,ClientServiceAff,$routeParams) {
            ClientMdpService.fetchMdpClient($routeParams.id).then(function(d) {
            	console.log(">> fetchMdpClient");
                self.user = d;
                $scope.user = d;
                console.log($scope.user);
            }, function(errResponse) {
                console.error('Error while fetching Users');
            });
            console.log("hello id");
            $scope.valid= function(client, id){
                console.log(client);
                ClientServiceAff.validUser(client, id)
                }
        } ]);
//affectCtrl
app.controller('affectCtrl',  ['$scope', '$location','ClientService','EmployeService','ClientServiceNew','$routeParams',
          function($scope, $location, ClientService,EmployeService,ClientServiceNew,$routeParams){
            console.log("welcome");
            ClientServiceNew.fetchNewClients()
                .then(
                function(d) {
                    self.users = d;
                    $scope.users=d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
            console.log("hello2");
            EmployeService.fetchConseiller()
                .then(
                function(d) {
                    self.cons = d;
                    $scope.cons=d;
                    console.log($scope.cons);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );  
            console.log("hello id");
            $scope.submit= function(idcl,idcons){
                console.log(idcl);
                console.log(idcons);                
                ClientServiceNew.updateUser(idcl,idcons)     
               }
        }]);

//rootCtrl
app.controller('rootCtrl', ['$scope', '$location', 'ClientMdpService', '$window','$routeParams',
    function($scope, $location,ClientMdpService, $window,$routeParams) {
        var d = null;
        var pers = null;
        d =sessionStorage.getItem("pers");
        pers = JSON.parse(d);
        if(pers!=null){
        $scope.util=pers;
            console.log(pers);
        console.log("rootCtrl::");
        console.log("=============");
        ClientMdpService.fetchMdpClient($routeParams.id).then(function(d) {
            self.user = d;
            $scope.news = d;
            console.log($scope.news);
        }, function(errResponse) {
            console.error('Error while fetching Users');
        });}else{
            $window.location = 'index.html#';
        }
    }]);

app.controller('newClCtrl', ['$scope', '$location', 'ClientService', 'UploadFileService', 
                              function($scope, $location, ClientService, UploadFileService) {
	
	$scope.client = {};



  //$scope.sid =
  $scope.sid = Math.floor(Math.random() * 100000000);
  $scope.uploadFile = function(files, sid, key) { //, field) {
	  var fd = new FormData();
	  console.log('kokfdfksjfksdf');
	  console.log(sid);
	  //Take the first selected file
		//fd.append("file", files[0]);

  	//UploadFileService.uploadFile(fd, sid, key);
  }; 
     
    $scope.submit = function(client) {
      console.log(client);
      client.comptes[0].transactions[0].dateDebut=new Date();
      client.comptes[0].transactions[0].dateFin=client.comptes[0].transactions[0].dateDebut;
      client.comptes[0].transactions[0].type=1;
      ClientService.createClient(client);
    };

}]);

app.controller('notif_ctrl', ['$scope', '$location', 'NotificationService',
                             function($scope, $location, NotificationService) {
    
    console.log("hello");
    //$scope.pub = {};

   $scope.submit = function(pub) {
     console.log(pub);
     NotificationService.createNotif(pub);
   };

}]);

app.controller('newConsCtrl', ['$scope', '$location', 'EmployeService','$window',
                             function($scope, $location, EmployeService,$window) {
    $scope.cons = {};

   $scope.submit = function(cons) {
     console.log("test");
     EmployeService.createCons(cons);
     $window.location = 'accueil.html#/search_ad';
   };

}]);
app.controller(

'searchNomCtrl',
[
        '$scope',
        '$location',
        'ClientService',
        
        function($scope, $location, ClientService) {
            $scope.client = {};
            
            $scope.src = function(client){
            console.log(client);
            
            ClientService.srcClient(client) 
                        .then
                            (function(d) {
                                    $scope.users = d;                                                   
                                    console
                                            .log($scope.users);
                                                                                        
                                },
                                function(errResponse) {
                                    console.error = ("Invalid entry");
                                });                     

            }} ]);






app.controller('mainCtrl', function($scope) {});
app.controller('contactCtrl', ['$scope', '$routeParams', '$location','$q','ClientService', 'ClientServiceNew',
    function($scope, $routeParams, $location,$q, ClientService,ClientServiceNew) {
    var self = this;
    self.users=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


        /*$scope.sujet = $routeParams.sujet;
        $scope.msg = $routeParams.msg;
        //$scope.absUrl  = $location.absUrl();
        $scope.fichiers = $routeParams.fichiers;
        /*console.log("contactCtrl::sujet="+$routeParams.sujet);
        console.log("contactCtrl::msg="+$routeParams.msg);
        console.log("contactCtrl::files="+$routeParams.files);*/                               
}]);

//app.controller('root', [ 
//                          function($scope, $location) {
//                          var d =sessionStorage.getItem("pers");
//                          var pers = JSON.parse(d);
//                          $scope.util=pers;
//                              console.log(pers.type);
//                              // todo
//                              //$location.url("views/see.html");
//                              //$scope.role = 1;
//                          }]);


/*
app.controller('connectCtrl', ['$scope', '$location', 
    function($scope, $location) {
        //$location.url('/tpl');
        console.log("connectCtrl::=");
    }]);
*/
// register : no params

app.controller('registerCtrl', ['$scope', '$location', 
    function($scope, $location) {
        //$location.url('/tpl');
        console.log("registerCtrl::");
    }]);

app.controller('addCtrl', ['$scope', '$location', 
    function($scope, $location) {
        //$location.url('/tpl');
        console.log("addCtrl::");
    }]);


app.controller('askCtrl', ['$scope', '$location', 
    function($scope, $location) {
        //$location.url('/tpl');
        console.log("askCtrl::");
    }]);

app.controller('seeCtrl', ['$scope', '$location', 'CompteService','$routeParams',
                           function($scope, $location, CompteService, $routeParams) {
    console.log("Compte view");
    }]);

app.controller('see_clCtrl', [ '$scope', '$location', 'ClientIdService','CompteService','ClientUpdateService',
                            '$routeParams',
                            function($scope, $location, ClientIdService, CompteService,ClientUpdateService, $routeParams) {
                                console.log("hello toi");
                                ClientIdService.searchClient($routeParams.id).then(function(d) {
                                    self.user = d;
                                    $scope.user = d;
                                    console.log($scope.user);
                                }, function(errResponse) {
                                    console.error('Error while fetching Users');
                                });
                                
                                $scope.send= function (client,id){
                               	 console.log(client);
                               	 console.log(id);
                               	 ClientUpdateService.UpdateClient(client,id);
                               };    
                                $scope.commande= function(cpt){
                                    console.log("test");  
                                    CompteService.commanderChequier(cpt)   
                                   }
                            } ]);

app.controller('compteCtrl', [ '$scope', '$location', 'CompteService','$routeParams',
                               function($scope, $location, CompteService, $routeParams) {
                                   console.log("hello id");
                                   CompteService.fetchCompte($routeParams.cpt).then(function(d) {
                                       self.cpt= d;
                                       console.log("cpt : " + cpt);
                                       $scope.cpt = d;
                                       console.log($scope.cpt);
                                   }, function(errResponse) {
                                       console.error('Error while fetching Compte');
                                   });
                                   $scope.PrintElem=function printToCart(printSectionId){
                                       var innerContents = document.getElementById(printSectionId).innerHTML;
                                       var popupWindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
                                       popupWindow.document.open();
                                       popupWindow.document.write('<html><head><link rel="stylesheet" type="text/css" href="static/css/print.css" window.print(); /></head><body >' + innerContents + '</html>');
                                       popupWindow.document.close();
                              	 	};
                               } ]);

app.controller('see_conCtrl', [ '$scope', '$location', 'EmployeService','ConseillerUpdateService',
                                '$routeParams',
                                function($scope, $location, EmployeService,ConseillerUpdateService, $routeParams) {
                                    console.log("hello id");
                                    EmployeService.searchCons($routeParams.id).then(function(d) {
                                        self.cons = d;
                                        $scope.cons = d;
                                        console.log($scope.user);
                                    }, function(errResponse) {
                                        console.error('Error while fetching Users');
                                    });
                                    $scope.sen= function (conseiller,id){
                                      	 console.log(conseiller);
                                       	 console.log(id);
                                       	 ConseillerUpdateService.UpdateConseiller(conseiller,id);
                                       }   
                                } ]);
//transfer 
app.controller('transferCtrl', ['$scope', '$location','CompteService',
    function($scope, $location,CompteService) {
	console.log=($scope.cpt_origine);
	$scope.submit = function (){
		var cpt1=$scope.cpt_origine.numCpt;
		var cpt2=$scope.cpt_dest;
		var mont=$scope.mont;
		CompteService.virer(cpt1, cpt2, mont);
	};
    }]);

//notify   
app.controller('notifyCtrl', ['$scope', '$location', 
    function($scope, $location) {
	var d = null;
	var compte= null;
	d =sessionStorage.getItem("pers");
	pers = JSON.parse(d);
	var compte = pers.comptes;
	$scope.notif=compte;
    }]);
    
//request       
app.controller('requestCtrl', ['$scope', '$location', 'ClientService',
    
    function($scope, $location, ClientService) {
		$scope.commanderChequier = function(clt,numCpt){
			ClientService.commanderChequier(clt,numCpt);
		}
        $scope.open=function(){
        	var util =$scope.util;
        	var mont= $scope.mont;
        	ClientService.OpenNewCompte(util.id, mont);
        }
    }]);

//print         
app.controller('printCtrl', ['$scope', '$location', 
    function($scope, $location) {
	$scope.PrintElem = function printToCart(printSectionId){
	          var innerContents = document.getElementById(printSectionId).innerHTML;
	          var popupWindow = window.open('', '_blank', 'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	          popupWindow.document.open();
	          popupWindow.document.write('<html><head><link rel="stylesheet" type="text/css" href="static/css/print.css" window.print(); /></head><body >' + innerContents + '</html>');
	          popupWindow.document.close();
	        };
    }]);

//update        
app.controller('updateCtrl', ['$scope', '$location', 
    function($scope, $location) {
        // todo
    }]);

//search_ag     
app.controller('search_agCtrl',  ['$scope', '$location','ClientService','$routeParams','ClientFilterService','$window',
  function($scope, $location, ClientService,$routeParams,ClientFilterService,$window){
    console.log("fetching All Clients");
    ClientFilterService.fetchFilteredClients($routeParams.id)
        .then(
        function(d) {
            self.users = d;
            $scope.users=d;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        }
    );
    
    $scope.sm = function (nom,prenom){
    	 console.log("Entered"); 
    ClientService.srcClient(nom,prenom)
    
    .then(
            function(d) {
                self.users = d;
                $scope.users=d;
                console.log($scope.users);
                
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
}
]);

//search_ad
app.controller('search_adCtrl', ['$scope', '$location','EmployeService','EmpSearchService',
     function($scope, $location, EmployeService,EmpSearchService)
     {
            console.log("hello2");
            EmployeService.fetchConseiller()
                .then(
                function(d) {
                    self.cons = d;
                    $scope.cons=d;
                    console.log($scope.cons);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            ); 
            
            $scope.srcCons = function (nom,prenom){
            	console.log(nom,prenom);
            	EmpSearchService.fetchCons(nom,prenom)
            	  .then(
                          function(d) {
                              self.cons = d;
                              $scope.cons=d;
                              console.log($scope.cons);
                          },
                          function(errResponse){
                              console.error('Error while fetching Users');
                          }
                      ); 
            }
}]);

                        
//consult_agCtrl
app.controller('consult_agCtrl', [ '$scope', '$location', 'ClientMdpService','ClientServiceAff','$routeParams',
        function($scope, $location, ClientMdpService,ClientServiceAff,$routeParams) {
            ClientMdpService.fetchMdpClient($routeParams.id).then(function(d) {
            	console.log(">> fetchMdpClient");
                self.user = d;
                $scope.user = d;
                console.log($scope.user);
            }, function(errResponse) {
                console.error('Error while fetching Users');
            });
            console.log("hello id");
            $scope.valid= function(client, id){
                console.log(client);
                ClientServiceAff.validUser(client, id)
                }
        } ]);
//affectCtrl
app.controller('affectCtrl',  ['$scope', '$location','ClientService','EmployeService','ClientServiceNew','$routeParams',
          function($scope, $location, ClientService,EmployeService,ClientServiceNew,$routeParams){
            console.log("welcome");
            ClientServiceNew.fetchNewClients()
                .then(
                function(d) {
                    self.users = d;
                    $scope.users=d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
            console.log("hello2");
            EmployeService.fetchConseiller()
                .then(
                function(d) {
                    self.cons = d;
                    $scope.cons=d;
                    console.log($scope.cons);
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );  
            console.log("hello id");
            $scope.submit= function(idcl,idcons){
                console.log(idcl);
                console.log(idcons);                
                ClientServiceNew.updateUser(idcl,idcons)     
               }
        }]);

//rootCtrl
app.controller('rootCtrl', ['$scope', '$location', 'ClientMdpService', '$window','$routeParams',
    function($scope, $location,ClientMdpService, $window,$routeParams) {
        var d = null;
        var pers = null;
        d =sessionStorage.getItem("pers");
        pers = JSON.parse(d);
        if(pers!=null){
        $scope.util=pers;
            console.log(pers);
        console.log("rootCtrl::");
        console.log("=============");
        ClientMdpService.fetchMdpClient($routeParams.id).then(function(d) {
            self.user = d;
            $scope.news = d;
            console.log($scope.news);
        }, function(errResponse) {
            console.error('Error while fetching Users');
        });}else{
            $window.location = 'index.html#';
        }
    }]);

app.controller('notif_ctrl', ['$scope', '$location', 'NotificationService',
                             function($scope, $location, NotificationService) {
    
    console.log("hello");
    //$scope.pub = {};

   $scope.submit = function(pub) {
     console.log(pub);
     NotificationService.createNotif(pub);
   };

}]);

app.controller('newConsCtrl', ['$scope', '$location', 'EmployeService','$window',
                             function($scope, $location, EmployeService,$window) {
    $scope.cons = {};

   $scope.submit = function(cons) {
     console.log("test");
     EmployeService.createCons(cons);
     $window.location = 'accueil.html#/search_ad';
   };

}]);
app.controller(

'searchNomCtrl',
[
        '$scope',
        '$location',
        'ClientService',
        
        function($scope, $location, ClientService) {
            $scope.client = {};
            
            $scope.src = function(client){
            console.log(client);
            
            ClientService.srcClient(client) 
                        .then
                            (function(d) {
                                    $scope.users = d;                                                   
                                    console
                                            .log($scope.users);
                                                                                        
                                },
                                function(errResponse) {
                                    console.error = ("Invalid entry");
                                });                     

            }} ]);






