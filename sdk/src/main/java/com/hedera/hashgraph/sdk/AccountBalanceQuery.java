package com.hedera.hashgraph.sdk;

import com.hedera.hashgraph.sdk.proto.CryptoGetAccountBalanceQuery;
import com.hedera.hashgraph.sdk.proto.CryptoServiceGrpc;
import com.hedera.hashgraph.sdk.proto.Query;
import com.hedera.hashgraph.sdk.proto.QueryHeader;
import com.hedera.hashgraph.sdk.proto.Response;
import com.hedera.hashgraph.sdk.proto.ResponseHeader;
import io.grpc.MethodDescriptor;

/**
 * Get the balance of a Hedera™ crypto-currency account. This returns only the balance, so it is a
 * smaller and faster reply than {@link AccountInfoQuery}.
 *
 * <p>This query is free.
 */
public final class AccountBalanceQuery extends QueryBuilder<Hbar, AccountBalanceQuery> {
    private final CryptoGetAccountBalanceQuery.Builder builder;

    public AccountBalanceQuery() {
        builder = CryptoGetAccountBalanceQuery.newBuilder();
    }

    /**
     * The account ID for which the balance is being requested.
     *
     * This is mutually exclusive with {@link #setContractId(ContractId)}.
     * @return This AccountBalanceQuery for chaining
     * @param accountId The AccountId to set
     */
    public AccountBalanceQuery setAccountId(AccountId accountId) {
        builder.setAccountID(accountId.toProtobuf());
        return this;
    }

    /**
     * The contract ID for which the balance is being requested.
     *
     * This is mutually exclusive with {@link #setAccountId(AccountId)}.
     */
    public AccountBalanceQuery setContractId(ContractId contractId) {
        builder.setContractID(contractId.toProtobuf());
        return this;
    }

    @Override
    boolean isPaymentRequired() {
        return false;
    }

    @Override
    void onMakeRequest(Query.Builder queryBuilder, QueryHeader header) {
        queryBuilder.setCryptogetAccountBalance(builder.setHeader(header));
    }

    @Override
    Hbar mapResponse(Response response) {
        return Hbar.fromTinybars(response.getCryptogetAccountBalance().getBalance());
    }

    @Override
    ResponseHeader mapResponseHeader(Response response) {
        return response.getCryptogetAccountBalance().getHeader();
    }

    @Override
    QueryHeader mapRequestHeader(Query request) {
        return request.getCryptogetAccountBalance().getHeader();
    }

    @Override
    MethodDescriptor<Query, Response> getMethodDescriptor() {
        return CryptoServiceGrpc.getCryptoGetBalanceMethod();
    }
}
