// SPDX-License-Identifier: Apache-2.0
package com.hedera.hashgraph.sdk;

import com.hedera.hashgraph.sdk.proto.QueryHeader;
import io.github.jsonSnapshot.SnapshotMatcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AccountRecordsQueryTest {
    @BeforeAll
    public static void beforeAll() {
        SnapshotMatcher.start(Snapshot::asJsonString);
    }

    @AfterAll
    public static void afterAll() {
        SnapshotMatcher.validateSnapshots();
    }

    @Test
    void shouldSerialize() {
        var builder = com.hedera.hashgraph.sdk.proto.Query.newBuilder();
        new AccountRecordsQuery()
                .setAccountId(AccountId.fromString("0.0.5005"))
                .setMaxQueryPayment(Hbar.fromTinybars(100_000))
                .onMakeRequest(builder, QueryHeader.newBuilder().build());
        SnapshotMatcher.expect(builder.build().toString().replaceAll("@[A-Za-z0-9]+", ""))
                .toMatchSnapshot();
    }
}
