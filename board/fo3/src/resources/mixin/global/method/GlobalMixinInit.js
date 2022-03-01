import Vue from "vue";
import GlobalMethods from "./GlobalMethods";

Vue.mixin({
    beforeCreate(){
        this.common = new GlobalMethods();
    }
})