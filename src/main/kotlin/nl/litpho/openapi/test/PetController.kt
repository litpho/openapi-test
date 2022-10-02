package nl.litpho.openapi.test

import com.atradius.openapi.test.api.PetsApi
import com.atradius.openapi.test.model.Pet
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PetController : PetsApi {

    override fun createPets(): ResponseEntity<Void> = ResponseEntity(HttpStatus.OK)

    override fun listPets(limit: Int?): ResponseEntity<MutableList<Pet>> {
        val pet = Pet().apply {
            id = 12L
            name = "bla $limit"
            tag = "gat"
        }
        return ResponseEntity.ok(mutableListOf(pet))
    }

    override fun showPetById(petId: String): ResponseEntity<Pet> {
        val pet = Pet().apply {
            id = petId.toLong()
            name = "bla"
            tag = "gat"
        }
        return ResponseEntity.ok(pet)
    }
}