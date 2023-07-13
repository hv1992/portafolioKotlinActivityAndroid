package com.portafolioHugoVillagra.portafoliokotlinactivity.modules.mainMenu.viewModels
import androidx.lifecycle.ViewModel
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.mainMenu.constants.OptionMenuCode
import com.portafolioHugoVillagra.portafoliokotlinactivity.modules.mainMenu.models.OptionMenuModel

class MainMenuViewModel : ViewModel(){
    val titleMainMenu : String = "Menu"

    val informationHeader : String = "Portafolio Kotlin Activity"

    //Esto es para declarar un listado de datos, que sería el listado de
    val listOptionMenu : List<OptionMenuModel> = listOf(
        OptionMenuModel(iconName = "option_menu_random_cat_image", informationOption = "Random Cats Image", optionCode = OptionMenuCode.catImage),
        OptionMenuModel(iconName = "option_menu_random_dog_image", informationOption = "Random Dog Image", optionCode = OptionMenuCode.dogImage)
    )
}