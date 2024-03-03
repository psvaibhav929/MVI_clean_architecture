package com.lloyd.domain.mockdata

import com.google.gson.Gson
import com.lloyd.data.dto.DogBreedDto
import com.lloyd.data.dto.DogDetailsDto


val dogBreedsMockJson = """
    {
        "message": {
            "affenpinscher": [],
            "african": [],
            "airedale": [],
            "akita": [],
            "appenzeller": [],
            "australian": [
                "kelpie",
                "shepherd"
            ],
            "bakharwal": [
                "indian"
            ],
            "basenji": [],
            "beagle": [],
            "bluetick": [],
            "borzoi": [],
            "bouvier": [],
            "boxer": [],
            "brabancon": [],
            "briard": [],
            "buhund": [
                "norwegian"
            ],
            "bulldog": [
                "boston",
                "english",
                "french"
            ],
            "bullterrier": [
                "staffordshire"
            ],
            "cattledog": [
                "australian"
            ],
            "cavapoo": [],
            "chihuahua": [],
            "chippiparai": [
                "indian"
            ],
            "chow": [],
            "clumber": [],
            "cockapoo": [],
            "collie": [
                "border"
            ],
            "coonhound": [],
            "corgi": [
                "cardigan"
            ],
            "cotondetulear": [],
            "dachshund": [],
            "dalmatian": [],
            "dane": [
                "great"
            ],
            "deerhound": [
                "scottish"
            ],
            "dhole": [],
            "dingo": [],
            "doberman": [],
            "elkhound": [
                "norwegian"
            ],
            "entlebucher": [],
            "eskimo": [],
            "finnish": [
                "lapphund"
            ],
            "frise": [
                "bichon"
            ],
            "gaddi": [
                "indian"
            ],
            "germanshepherd": [],
            "greyhound": [
                "indian",
                "italian"
            ],
            "groenendael": [],
            "havanese": [],
            "hound": [
                "afghan",
                "basset",
                "blood",
                "english",
                "ibizan",
                "plott",
                "walker"
            ],
            "husky": [],
            "keeshond": [],
            "kelpie": [],
            "kombai": [],
            "komondor": [],
            "kuvasz": [],
            "labradoodle": [],
            "labrador": [],
            "leonberg": [],
            "lhasa": [],
            "malamute": [],
            "malinois": [],
            "maltese": [],
            "mastiff": [
                "bull",
                "english",
                "indian",
                "tibetan"
            ],
            "mexicanhairless": [],
            "mix": [],
            "mountain": [
                "bernese",
                "swiss"
            ],
            "mudhol": [
                "indian"
            ],
            "newfoundland": [],
            "otterhound": [],
            "ovcharka": [
                "caucasian"
            ],
            "papillon": [],
            "pariah": [
                "indian"
            ],
            "pekinese": [],
            "pembroke": [],
            "pinscher": [
                "miniature"
            ],
            "pitbull": [],
            "pointer": [
                "german",
                "germanlonghair"
            ],
            "pomeranian": [],
            "poodle": [
                "medium",
                "miniature",
                "standard",
                "toy"
            ],
            "pug": [],
            "puggle": [],
            "pyrenees": [],
            "rajapalayam": [
                "indian"
            ],
            "redbone": [],
            "retriever": [
                "chesapeake",
                "curly",
                "flatcoated",
                "golden"
            ],
            "ridgeback": [
                "rhodesian"
            ],
            "rottweiler": [],
            "saluki": [],
            "samoyed": [],
            "schipperke": [],
            "schnauzer": [
                "giant",
                "miniature"
            ],
            "segugio": [
                "italian"
            ],
            "setter": [
                "english",
                "gordon",
                "irish"
            ],
            "sharpei": [],
            "sheepdog": [
                "english",
                "indian",
                "shetland"
            ],
            "shiba": [],
            "shihtzu": [],
            "spaniel": [
                "blenheim",
                "brittany",
                "cocker",
                "irish",
                "japanese",
                "sussex",
                "welsh"
            ],
            "spitz": [
                "indian",
                "japanese"
            ],
            "springer": [
                "english"
            ],
            "stbernard": [],
            "terrier": [
                "american",
                "australian",
                "bedlington",
                "border",
                "cairn",
                "dandie",
                "fox",
                "irish",
                "kerryblue",
                "lakeland",
                "norfolk",
                "norwich",
                "patterdale",
                "russell",
                "scottish",
                "sealyham",
                "silky",
                "tibetan",
                "toy",
                "welsh",
                "westhighland",
                "wheaten",
                "yorkshire"
            ],
            "tervuren": [],
            "vizsla": [],
            "waterdog": [
                "spanish"
            ],
            "weimaraner": [],
            "whippet": [],
            "wolfhound": [
                "irish"
            ]
        },
        "status": "success"
    }
""".trimIndent()

private val gson by lazy { Gson() }

fun fetchDogBreedsMockData(): com.lloyd.data.dto.DogBreedDto {
    return gson.fromJson<com.lloyd.data.dto.DogBreedDto>(dogBreedsMockJson, com.lloyd.data.dto.DogBreedDto::class.java)
}

val dogDetailsMockJson = """
    {"message":"https:\/\/images.dog.ceo\/breeds\/affenpinscher\/n02110627_2748.jpg","status":"success"}
""".trimIndent()

fun fetchDogDetailsMockData(): com.lloyd.data.dto.DogDetailsDto {
    return gson.fromJson<com.lloyd.data.dto.DogDetailsDto>(dogDetailsMockJson, com.lloyd.data.dto.DogDetailsDto::class.java)
}