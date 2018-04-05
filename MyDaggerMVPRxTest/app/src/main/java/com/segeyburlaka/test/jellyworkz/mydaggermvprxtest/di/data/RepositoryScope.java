package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.di.data;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface RepositoryScope {
}