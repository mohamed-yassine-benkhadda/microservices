/*
 * Copyright 2019 Mahmoud Romeh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.resilience4j.spring6.bulkhead.configure;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.spring6.bulkhead.configure.RxJava2BulkheadAspectExt;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * aspect unit test
 */
@RunWith(MockitoJUnitRunner.class)
public class RxJava2BulkheadAspectExtTest {

    @Mock
    ProceedingJoinPoint proceedingJoinPoint;

    @InjectMocks
    RxJava2BulkheadAspectExt rxJava2BulkheadAspectExt;


    @Test
    public void testCheckTypes() {
        assertThat(rxJava2BulkheadAspectExt.canHandleReturnType(Flowable.class)).isTrue();
        assertThat(rxJava2BulkheadAspectExt.canHandleReturnType(Single.class)).isTrue();
    }

    @Test
    public void testReactorTypes() throws Throwable {
        Bulkhead bulkhead = Bulkhead.ofDefaults("test");

        when(proceedingJoinPoint.proceed()).thenReturn(Single.just("Test"));
        assertThat(rxJava2BulkheadAspectExt.handle(proceedingJoinPoint, bulkhead, "testMethod"))
            .isNotNull();

        when(proceedingJoinPoint.proceed()).thenReturn(Flowable.just("Test"));
        assertThat(rxJava2BulkheadAspectExt.handle(proceedingJoinPoint, bulkhead, "testMethod"))
            .isNotNull();
    }


}