<template>
<div class="top">
    <v-flex>
        <v-card class="container">
            <v-flex>
                <h2>Create a memo form</h2>
                <v-flex style="margin: 24px;" xs12 sm6 offset-sm3>
                    <v-textarea
                    v-model="memo"
                    outline
                    label="Memo-memo"/>
                    <v-btn
                    @click="onRegist"
                    :loading="isLoading"
                    color="purple"
                    class="white--text">
                    Register
                    </v-btn>
                </v-flex>
            </v-flex>
            <v-card class="container">
                <v-flex>
                    <h3>registererd memo</h3>
                    <v-flex style="margin: 24px;">
                        <v-btn
                        @click="getItems"
                        :loading="isLoading"
                        color="black"
                        class="white--text">
                        Read
                        </v-btn>
                        <v-data-table
                        :headers="headers"
                        :items="items"
                        :pagination.sync="pagination"
                        no-data-text="">
                        <template
                        slot="items"
                        slot-scope="props">
                        <tr>
                            <td>{{ props.item.uid }}</td>
                            <td><span v-html="props.item.memo"/></td>
                            <td>{{ props.item.createdAt.toDate() | dateFormat }}</td>
                            <td>{{ props.item.updatedAt.toDate() | dateFormat }}</td>
                        </tr>
                        </template>
                        </v-data-table>
                    </v-flex>
                </v-flex>
            </v-card>
        </v-card>
    </v-flex>
</div>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import firebase, { database } from 'firebase/app'
import { format } from 'date-fns'
@Component({
    name: 'CreateFromPage',
    filters: {
        dateFormat(date: Date){
            return format(date, 'YYYY/MM/DD HH:mm:ss');
        },
    },
})
export default class CreateFromPage extends Vue{
    isLoading: boolean = false

    // register data
    memo: string = ''
    // register list
    items: any[] = []
    // v-date-table
    headers: any[] = [
        { text: 'uid', value: 'uid'},
        { text: 'memo', value: 'memo'},
        { text: 'createdAt', value: 'createdAt'},
        { text: 'updatedAt', value: 'updatedAt'},
    ]
    // data
    selectRowsPerPage: number = 5
    pagination: any={
        sortBy: 'createdAt',
        descending: true,
        rowsPerPage: this.selectRowsPerPage,
    }
    @Watch('selectRowsPerPage')
    onChangeSelecgtRowsPerPage(newVal: number){
        this.pagination.rowsPerPage = newVal
    }
    mountd(){
        this.getItems()
    }

    // register
    async onRegist(){
        this.isLoading=true
        await this.writeFirestore()
        await this.getItems()
        this.clear()
        this.isLoading = false
    }

    // retrieve
    async getItems(){
        console.log('getItems')
        this.isLoading = true
        await this.readFirestore()
        this.isLoading = false
    }

    // clear a form
    clear(){
        this.memo = ''
    }

    // Write data in Fireastore
    async writeFirestore(){
        try{
            const db: firebase.firestore.Firestore = firebase.firestore()
            const collection: firebase.firestore.CollectionReference = db.collection('version/1/memo')
            const id: string = collection.doc().id
            const result = await collection.doc(id).set({
                uid: id,
                createdAt: new Date(),
                updatedAt: new Date(),
                memo: this.memo,
            })
        } catch (error){
            console.error('firebase error', error)
        }
    }

    // retrieve data from Firestore
    async readFirestore(){
        try{
            this.items = []
            const db: firebase.firestore.Firestore = firebase.firestore()
            const items: firebase.firestore.QuerySnapshot = await db.collection('version/1/memo').get()
            items.docs.forEach((item: firebase.firestore.QueryDocumentSnapshot) =>{
                if (item.exists){
                    const data = item.data()
                    if ('memo' in data){
                        data.memo = data.memo.replace(/\n/g, '<br>')
                    }
                    this.items.push(data)
                }
            })
            console.log(this.items)
        } catch (error){
            console.error('firebase error', error)
        }
    }
}
</script>
<style lang="stylus">
.top
margin 10px
.container
    text-align left
    margin-top 20px
.subtitle
    pading-left 12px
</style>