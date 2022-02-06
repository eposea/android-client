package xyz.savvamirzoyan.eposea.core

sealed interface Model {
    interface Cloud : Model
    interface Local : Model
    interface Data : Model
    interface Domain : Model
    interface Ui : Model
}