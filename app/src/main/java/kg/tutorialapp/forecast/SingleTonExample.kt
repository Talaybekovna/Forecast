package kg.tutorialapp.forecast

// UROK 50
class SingleTonExample private constructor() {

    companion object{
        private val instance = SingleTonExample()

        fun getInstance() = instance
    }
}



/*
companion object{
    private var instance: SingleTonExample? = null

    fun getInstance(): SingleTonExample {
        if (instance == null) instance = SingleTonExample()

        return instance!!
    }
}*/
