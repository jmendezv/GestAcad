package com.example.demo.app

import com.example.demo.controllers.AlumnesController
import com.example.demo.controllers.GrupsController
import com.example.demo.controllers.ProfesorsController
import javafx.collections.*
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import tornadofx.*

class Main: View() {

    override val root: BorderPane by fxml()

    //CONTROLLERS
    val grupcontroler: GrupsController by inject()
    val alumnecontroler: AlumnesController by inject()
    val profesorscontroler: ProfesorsController by inject()

    //TABS
    val Tb_grups:Tab by fxid("Tb_grups")

    //Buttons
    val Bt_afegir:Button by fxid("grupsBtnAfegirAlumnes")
    val Bt_eliminar:Button by fxid("grupsBtnTreureAlumnes")

    //TABLEVIEWS
    val Tv_grups:javafx.scene.control.TableView<Grups> by fxid("grupsTableGrups")
    val Tv_alumne:javafx.scene.control.TableView<Alumne> by fxid("grupsTableAlumnes")
    val Tv_alumne2:javafx.scene.control.TableView<Alumne> by fxid("grupsTableAlumnes2")
    val Tv_professors:javafx.scene.control.TableView<Alumne> by fxid("alumnesTableProfessors")

    //TABLECOLUMNS
    /*val Tc_id:TableColumn<TableView<Grups>,Int> by fxid("Cl_id")
    val Tc_nom:TableColumn<TableView<Grups>,String> by fxid("Cl_nom")
    val Tc_descripcio:TableColumn<TableView<Grups>,String> by fxid("Cl_descripcio")*/

    //COLLECTIONS
    var llistatAlumnes: MutableList<Alumne> = ArrayList()
    var llistatGrups: MutableList<Grups> = ArrayList()
    var llistatProfessor: MutableList<Alumne> = ArrayList()
    var alumnesSeleccionats: MutableList<Alumne> = ArrayList()

    //Variables canviants i aillades.
    var grupEscollit:Grups?=null
    var alumneEscollit:Alumne?=null

    init{
        llistatGrups = grupcontroler.obteGrups()
        println("llistat grups: "+llistatGrups)
        llistatAlumnes = alumnecontroler.obteAlumnes()
        var g = FXCollections.observableArrayList(llistatGrups.observable())
        var a = FXCollections.observableArrayList(llistatAlumnes.observable())
        var aS = FXCollections.observableArrayList(alumnesSeleccionats.observable())
        with(root){

            with(Tv_grups) {
                Tv_grups.items=g
                //t = tableview(g) {
                    column("ID", Grups::idProperty).isEditable
                    column("Nom", Grups::nomProperty).isEditable
                    column("Descripció", Grups::descripcioProperty).isEditable
                    isEditable = true
                    prefHeight = 266.0
                    prefWidth = 311.0
                    layoutX = 370.0
                    layoutY = 100.0
               // }

            }

            with(Tv_alumne){
                Tv_alumne.items = a
                column("ID", Alumne::idProperty).isEditable
                column("Nom", Alumne::nomProperty).isEditable
                column("Cognoms", Alumne::cognomsProperty).isEditable
                column("Dni", Alumne::dniProperty).isEditable
                column("Data naixement", Alumne::datanaixementProperty).isEditable
                column("sexe", Alumne::sexeProperty).isEditable
                column("Telefon", Alumne::telefonProperty).isEditable
                column("Email", Alumne::idProperty).isEditable
                column("Descripció", Alumne::descripcioProperty).isEditable
                isEditable = true
                prefHeight = 311.0
                prefWidth = 246.0
                layoutX = 530.0
                layoutY = 100.0
            }

            with(Tv_alumne2){
                Tv_alumne2.items = aS
                column("ID", Alumne::idProperty)
                column("Nom", Alumne::nomProperty)
                column("Cognoms", Alumne::cognomsProperty)
                column("Dni", Alumne::dniProperty)
                column("Data naixement", Alumne::datanaixementProperty)
                column("sexe", Alumne::sexeProperty)
                column("Telefon", Alumne::telefonProperty)
                column("Email", Alumne::idProperty)
                column("Descripció", Alumne::descripcioProperty)
                isEditable = true
                prefHeight = 311.0
                prefWidth = 198.0
                layoutX = 530.0
                layoutY = 100.0
            }

            Tv_grups.onUserSelect {
                grupEscollit = Tv_grups.selectedItem
                println("El grup seleccionat es: " + grupEscollit)
            }

            Tv_alumne.onUserSelect {
                alumneEscollit=Tv_alumne.selectedItem
                println("Alumne escollit: " + alumneEscollit)
            }

            Bt_afegir.setOnMouseClicked {
                println("Alumne a afegir: " + alumneEscollit)
                alumnesSeleccionats.add(alumneEscollit!!)
                //Tv_alumne2.items = aS
            }

            Bt_eliminar.setOnMouseClicked {
                println("Alumne a esborrar: " + alumneEscollit)
                alumnesSeleccionats.remove(alumneEscollit!!)
            }
                }
            }
        }
